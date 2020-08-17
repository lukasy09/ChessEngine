public class MoveGenerator {
    private RayAttacks rayAttacks;

    public MoveGenerator(){
        rayAttacks = RayAttacks.getInstance();
    }

    Long generatePieceMoves(PieceType pieceType, int pieceIndex, long occupied){
        if(pieceType.equals(PieceType.PAWN)){
            throw new UnsupportedOperationException();
        }

        long ts = rayAttacks.getAttackMoves().get(pieceType).get(pieceIndex);
        for(long b = occupied & rayAttacks.getBlockersAndBeyond().get(pieceType).get(pieceIndex); b != 0L; b = b & (b-1)){
            int sq = Long.numberOfTrailingZeros(b);
            ts = ts & ~rayAttacks.getBehind()[pieceIndex][sq];
        }
        return ts;
    }

    public static void main(String[] args) {
        MoveGenerator moveGenerator = new MoveGenerator();

        System.out.println(RayAttacks.displayAsFormattedBinary(132096L));
        System.out.println("");

        long moves = moveGenerator.generatePieceMoves(PieceType.QUEEN, 10, 132096L);
        System.out.println(RayAttacks.displayAsFormattedBinary(moves));
    }

}
