class HopfieldNetwork {
    private int[][] weights;
    private int size;

    public HopfieldNetwork(int size) {
        this.size = size;
        this.weights = new int[size][size];
    }

    public void train(int[] pattern) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i != j) {
                    weights[i][j] += pattern[i] * pattern[j];
                }
            }
        }
    }

    public int[] recognize(int[] inputPattern) {
        int[] outputPattern = inputPattern.clone();
        boolean stable;
        do {
            stable = true;
            for (int i = 0; i < size; i++) {
                int sum = 0;
                for (int j = 0; j < size; j++) {
                    sum += weights[i][j] * outputPattern[j];
                }
                int newState = sum >= 0 ? 1 : -1;
                if (outputPattern[i] != newState) {
                    stable = false;
                    outputPattern[i] = newState;
                }
            }
        } while (!stable);
        return outputPattern;
    }
}
