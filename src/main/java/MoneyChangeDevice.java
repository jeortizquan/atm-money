import java.util.*;

public class MoneyChangeDevice {

    private class BillType implements Comparable<BillType> {
        private int moneyValue;
        private int moneyAmount;

        public BillType(int value, int amount) {
            this.moneyValue = value;
            this.moneyAmount = amount;
        }

        public int getMoneyValue() {
            return moneyValue;
        }

        public void setMoneyValue(int moneyValue) {
            this.moneyValue = moneyValue;
        }

        public int getMoneyAmount() {
            return moneyAmount;
        }

        public void setMoneyAmount(int moneyAmount) {
            this.moneyAmount = moneyAmount;
        }

        @Override
        public int compareTo(BillType billType) {
            return (this.getMoneyValue() > billType.getMoneyValue() ? -1 :
                    (this.getMoneyValue() == billType.getMoneyValue() ? 0 : 1));
        }
    }

    private List<BillType> mcdState;
    private HashMap<Integer, Integer> mcdHash;
    /**
     * Constructor for MoneyChangeDevice class defining its initial slots state
     *
     * @param initialSetup the initial money change device slots setup following the format described in this test description. *
     */
    public MoneyChangeDevice(String initialSetup) {
        String[] moneySetup = initialSetup.split(" ");
        mcdState = new ArrayList<>();
        mcdHash = new HashMap();
        for (int i = 0; i < moneySetup.length; i++) {
            String[] billSetup = moneySetup[i].split(":");

            mcdState.add(new BillType(Integer.parseInt(billSetup[0]), Integer.parseInt(billSetup[1])));
            mcdHash.put(Integer.parseInt(billSetup[0]), Integer.parseInt(billSetup[1]));
        }
        Collections.sort(mcdState);
    }

    /**
     * Calculates the money change for a given amount, <b>returning the least amount of bills</b>.<br />
     * A successful call to this method will change the current money slots state, comprising of the remaining bills for each slot.
     *
     * @param amount The amount to be changed following the format described in this test description.
     * @return The money change and the resulting change slots setup as per this test description.<br />
     * In case of no possible change, please also refer this test description
     */
    public String makeChangeForAmount(String amount) {
        String resultingState = "";
        List<BillType> result = new ArrayList<>();
        int amountNumber = Integer.parseInt(amount);

        String values = findCombinations(mcdState, Integer.parseInt(amount));
        for (int i = 0; i < values.length(); i++) {
            int key = Integer.parseInt(String.valueOf(values.charAt(i)));
            mcdHash.put(key, mcdHash.get(key) - 1);
        }
        return resultingState;
    }

    public String findCombinations(List<BillType> state, int amountNumber) {
        ArrayList<String> resultArray = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < state.size(); i++) {
            BillType bt = state.get(i);
            for (int j = 0; j < bt.moneyAmount; j++) {
                sb.append(bt.moneyValue);
            }
        }
        System.out.println(sb.toString());
        resultArray = combinations(sb.toString());
        resultArray.sort(Comparator.comparingInt(String::length));
        boolean found = false;
        int i = 0;
        while( i < resultArray.size() && !found) {
            if (findSomOfString(resultArray.get(i)) == amountNumber)
                found = true;
            i++;
        }
        if(found)
        System.out.println(resultArray.get(i-1));
        return resultArray.get(i-1);
    }

    public int findSomOfString(String sequence) {
        int result = 0;
        for (int i = 0; i < sequence.length(); i++) {
            result += Integer.parseInt(String.valueOf(sequence.charAt(i)));
        }
        return result;
    }

    public ArrayList<String> combinations(String value) {
        ArrayList<String> result = new ArrayList<>();
        for(int i = 0; i < value.length(); i++) {
            for(int j = i; j < value.length(); j++) {
                result.add(value.substring(i, j+1));
            }
        }
        return result;
    }

    /* public int combinations(int n, int r) {
        return factorial(n) / (factorial(r) * factorial(n - r));
    }
*/
    // You are allowed to create more methods to solve the challenge

}

