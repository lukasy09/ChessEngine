import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class RayAttacks {
    private static RayAttacks instance = null;
    private Map<Integer,Map<Direction,Long>> attackRayMasks;
    private Map<PieceType,Map<Integer,Long>> attackMoves;
    private Map<PieceType,Map<Integer,Long>> blockersAndBeyond;
    private Long[][] behind;

    private RayAttacks(){
        initializeRays();
        initializeAttackMoves();
        initializeBlockersAndBeyond();
        initializeBehind();
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
                Map<Integer, Long> pieceAttackMoves = new HashMap<>();
                attackRayMasks.forEach((key, value) -> pieceAttackMoves.put(key,
                        value.get(Direction.NE)
                                | value.get(Direction.SE)
                                | value.get(Direction.SW)
                                | value.get(Direction.NW)));
                attackMoves.put(pieceType,pieceAttackMoves);
            }
            else if(pieceType == PieceType.ROOK){
                Map<Integer, Long> pieceAttackMoves = new HashMap<>();
                attackRayMasks.forEach((key, value) -> pieceAttackMoves.put(key,
                        value.get(Direction.N)
                                | value.get(Direction.S)
                                | value.get(Direction.E)
                                | value.get(Direction.W)));
                attackMoves.put(pieceType,pieceAttackMoves);
            }
            else if(pieceType == PieceType.QUEEN){
                Map<Integer, Long> pieceAttackMoves = new HashMap<>();
                attackRayMasks.forEach((key, value) -> pieceAttackMoves.put(key,
                        value.get(Direction.N)
                                | value.get(Direction.S)
                                | value.get(Direction.E)
                                | value.get(Direction.W)
                                | value.get(Direction.NE)
                                | value.get(Direction.SE)
                                | value.get(Direction.SW)
                                | value.get(Direction.NW)));
                attackMoves.put(pieceType,pieceAttackMoves);
            }
        }
    }

    private void initializeBlockersAndBeyond(){
        blockersAndBeyond = new HashMap<>();
        for(PieceType pieceType : PieceType.values()) {
            if (pieceType == PieceType.BISHOP || pieceType == PieceType.ROOK || pieceType == PieceType.QUEEN) {
                Map<Integer, Long> blockersAndBeyondEntry = new HashMap<>();
                attackMoves.get(pieceType).forEach((key, value) -> blockersAndBeyondEntry.put(key,
                        value & 35604928818740736L));
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

        for(int i =0; i<64;i++){
            System.out.println(displayAsFormattedBinary(instance.behind[63][i]));
            System.out.println("");
        }
    }
}
