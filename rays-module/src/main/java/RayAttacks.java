import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class RayAttacks {
    private static RayAttacks instance = null;
    private Map<Integer,Map<Direction,Long>> attackRayMasks;

    private RayAttacks(){
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



    public static void main(String[] args) {
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
        for(Map.Entry<Integer,Map<Direction,Long>> entry : intance.attackRayMasks.entrySet()){
            System.out.println(entry.getKey());
            System.out.println(displayAsFormattedBinary(
                    entry.getValue().get(Direction.N)
                            | entry.getValue().get(Direction.NE)
                            | entry.getValue().get(Direction.E)
                            | entry.getValue().get(Direction.SE)
                            | entry.getValue().get(Direction.S)
                            | entry.getValue().get(Direction.SW)
                            | entry.getValue().get(Direction.W)
                            | entry.getValue().get(Direction.NW)
            ));
            System.out.println("");
        }
    }
}
