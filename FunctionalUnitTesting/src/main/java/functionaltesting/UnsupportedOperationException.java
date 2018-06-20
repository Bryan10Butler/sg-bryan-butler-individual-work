package functionaltesting;

public class UnsupportedOperationException {
    // Given a String and a non-negative int n, we'll say that the
    // front of the String is the first 3 chars, or whatever is there
    // if the String is less than length 3. Return n copies of the front;
    //
    // frontTimes("Chocolate", 2) -> "ChoCho"
    // frontTimes("Chocolate", 3) -> "ChoChoCho"
    // frontTimes("Abc", 3) -> "AbcAbcAbc"
    public String frontTimes(String str, int n) {

        //getting the first 3 characters of subString
        String subString = "";

        for (int i = 1; i <= n; i ++) {

            subString += str.substring(0,3);

        }
        return subString;
    }


}
