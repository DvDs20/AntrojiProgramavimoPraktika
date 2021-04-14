package Backend;

import java.time.LocalDate;

public class person {
    protected String name;
    protected String surname;
    protected String gender;

    public person(String name,String surname,String gender){
        this.name =name;
        this.surname = surname;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }


    public String getSurname() {
        return surname;
    }


    public String getGender() {
        return gender;
    }

}
