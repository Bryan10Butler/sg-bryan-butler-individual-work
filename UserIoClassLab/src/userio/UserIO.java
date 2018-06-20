package userio;

import java.math.BigDecimal;
import java.time.LocalDate;

//every interface is implied public and abstract
//abstract means you cannot provide an implementation for methods
//define the rules of the contract. Behind the scenes we don't care how it is done
public interface UserIO {

    void print(String message);

    double readDouble(String prompt);

    double readDouble(String prompt, double min, double max);

    float readFloat(String prompt);

    float readFloat(String prompt, float min, float max);

    int readInt(String prompt);

    int readInt(String prompt, int min, int max);

    long readLong(String prompt);

    long readLong(String prompt, long min, long max);

    String readString(String prompt);

    BigDecimal readBigDecimal(String prompt);

    LocalDate readLocalDate(String prompt);

}//end of interface UserIO
