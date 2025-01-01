class GarbageCollection {
    public static void main(String s[]) {
        try {
            Runtime rs = Runtime.getRuntime();

            System.out.println("Free memory in JVM (Java Virtual Machine) before Garbage Collection = " + rs.freeMemory() + " bytes");

            rs.gc();

            System.out.println("Free memory in JVM (Java Virtual Machine) after Garbage Collection = " + rs.freeMemory() + " bytes");

        } catch (Exception e) {
            System.out.println("An error occurred during garbage collection: " + e.getMessage());
        }
    }
}
