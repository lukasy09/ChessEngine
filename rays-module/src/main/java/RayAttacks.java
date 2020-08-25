import com.agh.technology.chess.engine.model.element.PieceType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class RayAttacks {
    private static RayAttacks instance = null;
    private Map<Integer,Map<Direction,Long>> attackRayMasks;
    private Map<PieceType,Map<Integer,Long>> attackMoves;
    private Map<PieceType,Map<Integer,Long>> blockersAndBeyond;
    private Map<Color,Map<Integer,Long>> pawnMoves;
    private Map<Color,Map<Integer,Long>> pawnCaptureMoves;
    private Long[][] behind;

    private RayAttacks(){
        initializeRays();
        initializeAttackMoves();
        initializeBlockersAndBeyond();
        initializeBehind();
        initializePawnMoves();
        initializePawnCaptureMoves();
    }

    private void initializeRays(){
        attackRayMasks = new HashMap<>();
        for(int i = 0; i < 64; i++){
            Map<Direction,Long> directionMaskMap = new HashMap<>();
            directionMaskMap.put(Direction.N, generateNorthRay(i));
            directionMaskMap.put(Direction.NE, generateNorthEastRay(i));
            directionMaskMap.put(Direction.E, generateEastRay(i));
            directionMaskMap.put(Direction.SE, generateSouthEastRay(i));
            directionMaskMap.put(Direction.S, generateSouthRay(i));
            directionMaskMap.put(Direction.SW, generateSouthWestRay(i));
            directionMaskMap.put(Direction.W, generateWestRay(i));
            directionMaskMap.put(Direction.NW, generateNorthWestRay(i));

            attackRayMasks.put(i, directionMaskMap);
        }
    }

    private void initializeAttackMoves(){
        attackMoves = new HashMap<>();
        for(PieceType pieceType : PieceType.values()){
            if(pieceType == PieceType.BISHOP){
                Map<Integer, Long> bishopAttackMoves = new HashMap<>();
                attackRayMasks.forEach((key, value) -> bishopAttackMoves.put(key,
                        value.get(Direction.NE)
                                | value.get(Direction.SE)
                                | value.get(Direction.SW)
                                | value.get(Direction.NW)));
                attackMoves.put(pieceType,bishopAttackMoves);
            }
            else if(pieceType == PieceType.ROOK){
                Map<Integer, Long> rookAttackMoves = new HashMap<>();
                attackRayMasks.forEach((key, value) -> rookAttackMoves.put(key,
                        value.get(Direction.N)
                                | value.get(Direction.S)
                                | value.get(Direction.E)
                                | value.get(Direction.W)));
                attackMoves.put(pieceType,rookAttackMoves);
            }
            else if(pieceType == PieceType.QUEEN){
                Map<Integer, Long> queenAttackMoves = new HashMap<>();
                attackRayMasks.forEach((key, value) -> queenAttackMoves.put(key,
                        value.get(Direction.N)
                                | value.get(Direction.S)
                                | value.get(Direction.E)
                                | value.get(Direction.W)
                                | value.get(Direction.NE)
                                | value.get(Direction.SE)
                                | value.get(Direction.SW)
                                | value.get(Direction.NW)));
                attackMoves.put(pieceType,queenAttackMoves);
            }
            else if(pieceType == PieceType.KNIGHT){
                Map<Integer, Long> knightAttackMoves = new HashMap<>();
                for(int index = 0; index < 64; index ++){
                    long attackMask = 0L;
                    if(index % 8 > 1 && index / 8 > 0){
                        attackMask = attackMask | 1L << (index - 10);
                    }
                    if(index % 8 > 0 && index / 8 > 1){
                        attackMask = attackMask | 1L << (index - 17);
                    }
                    if(index % 8 < 6 && index / 8 > 0){
                        attackMask = attackMask | 1L << (index - 6);
                    }
                    if(index % 8 < 7 && index / 8 > 1){
                        attackMask = attackMask | 1L << (index - 15);
                    }
                    if(index % 8 > 1 && index / 8 < 7){
                        attackMask = attackMask | 1L << (index + 6);
                    }
                    if(index % 8 > 0 && index / 8 < 6){
                        attackMask = attackMask | 1L << (index + 15);
                    }
                    if(index % 8 < 6 && index / 8 < 7){
                        attackMask = attackMask | 1L << (index + 10);
                    }
                    if(index % 8 < 7 && index / 8 < 6){
                        attackMask = attackMask | 1L << (index + 17);
                    }
                    knightAttackMoves.put(index, attackMask);
                }
                attackMoves.put(pieceType, knightAttackMoves);
            }
            else if(pieceType == PieceType.KING){
                Map<Integer, Long> kingAttackMoves = new HashMap<>();
                for(int index = 0; index < 64; index ++){
                    long attackMask = 0L;
                    if(index % 8 > 0 && index / 8 > 0){
                        attackMask = attackMask | 1L << (index - 9);
                    }
                    if(index % 8 > 0){
                        attackMask = attackMask | 1L << (index - 1);
                    }
                    if(index % 8 > 0 && index / 8 < 7){
                        attackMask = attackMask | 1L << (index + 7);
                    }
                    if(index / 8 > 0){
                        attackMask = attackMask | 1L << (index - 8);
                    }
                    if(index / 8 < 7){
                        attackMask = attackMask | 1L << (index + 8);
                    }
                    if(index % 8 < 7 && index / 8 > 0){
                        attackMask = attackMask | 1L << (index - 7);
                    }
                    if(index % 8 < 7){
                        attackMask = attackMask | 1L << (index + 1);
                    }
                    if(index % 8 < 7 && index / 8 < 7){
                        attackMask = attackMask | 1L << (index + 9);
                    }
                    kingAttackMoves.put(index, attackMask);
                }
                attackMoves.put(pieceType, kingAttackMoves);
            }
        }
    }

    private void initializeBlockersAndBeyond(){
        blockersAndBeyond = new HashMap<>();
        for(PieceType pieceType : PieceType.values()) {
            if (pieceType.equals(PieceType.BISHOP) || pieceType.equals(PieceType.ROOK) || pieceType.equals(PieceType.QUEEN)) {
                Map<Integer, Long> blockersAndBeyondEntry = new HashMap<>();
                attackMoves.get(pieceType).forEach((key, value) -> blockersAndBeyondEntry.put(key,
                        value & 35604928818740736L));
                blockersAndBeyond.put(pieceType, blockersAndBeyondEntry);
            } else if(!pieceType.equals(PieceType.PAWN)){
                Map<Integer, Long> blockersAndBeyondEntry = new HashMap<>();
                for(int i = 0; i < 64; i++){
                    blockersAndBeyondEntry.put(i, 0L);
                }
                blockersAndBeyond.put(pieceType, blockersAndBeyondEntry);
            }
        }
    }

    private void initializeBehind(){
        behind = new Long[64][64];
        for(int i = 0; i < 64; i++){
            for(int j = 0; j < 64; j++){
                if(i == j){
                    behind[i][j] = 0L;
                }else if(i > j){
                    behind[i][j] = ((generateSouthRay(i) & generateSouthRay(j))
                            | (generateWestRay(i) & generateWestRay(j))
                            | (generateSouthEastRay(i) & generateSouthEastRay(j))
                            | (generateSouthWestRay(i) & generateSouthWestRay(j)));
                }else{
                    behind[i][j] = (generateNorthRay(i) & generateNorthRay(j))
                            | (generateEastRay(i) & generateEastRay(j))
                            | (generateNorthEastRay(i) & generateNorthEastRay(j))
                            | (generateNorthWestRay(i) & generateNorthWestRay(j));
                }
            }
        }
    }

    private void initializePawnMoves(){
        pawnMoves = new HashMap<>();

        Map<Integer,Long> whitePawnMoves = new HashMap<>();
        for(int index = 0; index < 64; index++){
            if(index / 8 == 1){
                long move = 1L << index + 8 & 1L << index + 16;
                whitePawnMoves.put(index, move);
            }else if(index / 8 < 7){
                long move = 1L << index + 8;
                whitePawnMoves.put(index, move);
            }else{
                whitePawnMoves.put(index, 0L);
            }
        }

        Map<Integer,Long> blackPawnMoves = new HashMap<>();
        for(int index = 0; index < 64; index++){
            if(index / 8 == 6){
                long move = 1L << index - 8 & 1L << index - 16;
                whitePawnMoves.put(index, move);
            }else if(index / 8 > 0){
                long move = 1L << index - 8;
                whitePawnMoves.put(index, move);
            }else{
                whitePawnMoves.put(index, 0L);
            }
        }

        pawnMoves.put(Color.WHITE, whitePawnMoves);
        pawnMoves.put(Color.BLACK, blackPawnMoves);
    }

    private void initializePawnCaptureMoves(){
        pawnCaptureMoves = new HashMap<>();

        Map<Integer,Long> whitePawnCaptureMoves = new HashMap<>();
        for(int index = 0; index < 64; index++){
            long move = 0L;
            if(index / 8 < 7){
                if(index % 8 > 0){
                    move = move & 1L << index + 7;
                }
                if(index % 8 < 7){
                    move = move & 1L << index + 9;
                }
                whitePawnCaptureMoves.put(index, move);
            }else{
                whitePawnCaptureMoves.put(index, 0L);
            }
        }

        Map<Integer,Long> blackPawnCaptureMoves = new HashMap<>();
        for(int index = 0; index < 64; index++){
            long move = 0L;
            if(index / 8 > 0){
                if(index % 8 > 0){
                    move = move & 1L << index - 9;
                }
                if(index % 8 < 7){
                    move = move & 1L << index - 7;
                }
                blackPawnCaptureMoves.put(index, move);
            }else{
                blackPawnCaptureMoves.put(index, 0L);
            }
        }

        pawnCaptureMoves.put(Color.WHITE, whitePawnCaptureMoves);
        pawnCaptureMoves.put(Color.BLACK, blackPawnCaptureMoves);
    }

    public static RayAttacks getInstance(){
        if(instance == null){
            instance = new RayAttacks();
        }

        return instance;
    }


    public static String displayAsFormattedBinary(long value){
        String unreversedBoard = displayAsBinary(value).replaceAll("(.{8})", "$1\n");
        return String.join("\n", Arrays.stream(unreversedBoard.split("\n")).map(line -> new StringBuilder(line).reverse().toString()).collect(Collectors.toList()));
    }

    public static String displayAsBinary(long value){
        String binaryString = Long.toBinaryString(value);
        return String.format("%64s", binaryString).replace(' ', '0');
    }

    public static long generateNorthRay(int index){
        long mask = 1L << index;
        long ray = 0L;

        for(int i = index + 8; i < 64; i+=8){
            ray = ray | mask << i-index;
        }

        return ray;
    }

    public static long generateSouthRay(int index){
        long mask = 1L << index;
        long ray = 0L;

        for(int i = index - 8; i >= 0; i-=8){
            ray = ray | mask >>> index - i;
        }

        return ray;
    }

    public static long generateEastRay(int index){
        long mask = 1L << index;
        long ray = 0L;

        for(int i = index + 1; i % 8 != 0; i+=1){
            ray = ray | mask << i - index;
        }

        return ray;
    }

    public static long generateWestRay(int index){
        long mask = 1L << index;
        long ray = 0L;

        for(int i = index - 1; (i % 8 != 7) && i >=0; i-=1){
            ray = ray | mask >>> index - i;
        }

        return ray;
    }

    public static long generateNorthEastRay(int index){
        long mask = 1L << index;
        long ray = 0L;

        for(int i = index + 9; (i % 8 != 0) && i < 64; i+=9){
            ray = ray | mask << i - index;
        }

        return ray;
    }

    public static long generateNorthWestRay(int index){
        long mask = 1L << index;
        long ray = 0L;

        for(int i = index + 7; (i % 8 != 7) && i < 64; i+=7){
            ray = ray | mask << i - index;
        }

        return ray;
    }

    public static long generateSouthEastRay(int index){
        long mask = 1L << index;
        long ray = 0L;

        for(int i = index - 7; (i % 8 != 0) && i >=0; i-=7){
            ray = ray | mask >>> index-i;
        }

        return ray;
    }

    public static long generateSouthWestRay(int index){
        long mask = 1L << index;
        long ray = 0L;

        for(int i = index - 9; (i % 8 != 7) && i >=0; i-=9){
            ray = ray | mask >>> index-i;
        }

        return ray;
    }

    public Map<PieceType, Map<Integer, Long>> getAttackMoves() {
        return attackMoves;
    }

    public Map<PieceType, Map<Integer, Long>> getBlockersAndBeyond() {
        return blockersAndBeyond;
    }

    public Long[][] getBehind() {
        return behind;
    }

    public Map<Color, Map<Integer, Long>> getPawnMoves() {
        return pawnMoves;
    }

    public void setPawnMoves(Map<Color, Map<Integer, Long>> pawnMoves) {
        this.pawnMoves = pawnMoves;
    }

    public Map<Color, Map<Integer, Long>> getPawnCaptureMoves() {
        return pawnCaptureMoves;
    }

    public void setPawnCaptureMoves(Map<Color, Map<Integer, Long>> pawnCaptureMoves) {
        this.pawnCaptureMoves = pawnCaptureMoves;
    }

    public static void main(String[] args) {

        System.out.println(displayAsFormattedBinary(-9205322385119248384L));
        System.out.println("");
        System.out.println("");
        System.out.println("");

        int index = 63;
        System.out.println(displayAsFormattedBinary(generateNorthRay(index)));
        System.out.println("");
        System.out.println(displayAsFormattedBinary(generateSouthRay(index)));
        System.out.println("");
        System.out.println(displayAsFormattedBinary(generateEastRay(index)));
        System.out.println("");
        System.out.println(displayAsFormattedBinary(generateWestRay(index)));
        System.out.println("");
        System.out.println(displayAsFormattedBinary(generateNorthEastRay(index)));
        System.out.println("");
        System.out.println(displayAsFormattedBinary(generateNorthWestRay(index)));
        System.out.println("");
        System.out.println(displayAsFormattedBinary(generateSouthEastRay(index)));
        System.out.println("");
        System.out.println(displayAsFormattedBinary(generateSouthWestRay(index)));
        System.out.println("");

        System.out.println(displayAsFormattedBinary(
                generateNorthRay(index)
                        | generateNorthEastRay(index)
                        | generateEastRay(index)
                        | generateSouthEastRay(index)
                        | generateSouthRay(index)
                        | generateSouthWestRay(index)
                        | generateWestRay(index)
                        | generateNorthWestRay(index)
        ));

        RayAttacks intance = RayAttacks.getInstance();
//        for(Map.Entry<Integer,Map<Direction,Long>> entry : intance.attackRayMasks.entrySet()){
//            System.out.println(entry.getKey());
//            System.out.println(displayAsFormattedBinary(
//                    entry.getValue().get(Direction.N)
//                            | entry.getValue().get(Direction.NE)
//                            | entry.getValue().get(Direction.E)
//                            | entry.getValue().get(Direction.SE)
//                            | entry.getValue().get(Direction.S)
//                            | entry.getValue().get(Direction.SW)
//                            | entry.getValue().get(Direction.W)
//                            | entry.getValue().get(Direction.NW)
//            ));
//            System.out.println("");
//        }

//        for(Map.Entry<PieceType,Map<Integer,Long>> entry : intance.attackMoves.entrySet()){
//            System.out.println(entry.getKey());
//            for(Map.Entry<Integer,Long> entry1 : entry.getValue().entrySet()){
//                System.out.println(displayAsFormattedBinary(entry1.getValue()));
//                System.out.println("");
//            }
//            System.out.println("");
//        }

        System.out.println("");System.out.println("");System.out.println("Behind");System.out.println("");System.out.println("");

//        for(int i =0; i<64;i++){
//            System.out.println(displayAsFormattedBinary(instance.behind[63][i]));
//            System.out.println("");
//        }

        for(int i =0; i<64;i++){
            System.out.println(displayAsFormattedBinary(instance.attackMoves.get(PieceType.KING).get(i)));
            System.out.println("");
        }
    }
}
