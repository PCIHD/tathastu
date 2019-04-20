package com.miniproj.paragchaudhari.tathastu;
/*
1-Door
2-Window


Return 1 : optimal
       2 : ok
       3:  unacceptable

1-kitchen
2-hall
3-bedroom





*/

public class Object_validity {


    public void check_validation(int room, int object , int degree){

        switch(room) {
            case 1: // kitchen
                switch (object) {
                    case 1://door
                        if (degree >= 135 && degree <= 225) return 1;
                        else if (degree >= 45 && degree <= 135) return 2;
                        else return 3;
                        break;


                    case 2://window
                        if (degree >= 0 && degree <= 45) return 1;
                        else if (degree >= 235 && degree <= 305) return 2;
                        else return 3;
                        break;
                }
                return;

            case 2: // hall
                switch (object) {
                    case 1://door
                        if (degree >= 135 && degree <= 225) return 1;
                        else if (degree >= 45 && degree <= 135 || degree >= 225 && degree <= 305)
                            return 2;
                        else return 3;
                        break;

                    case 2://window
                        if (degree >= 135 && degree <= 225) return 1;
                        else if (degree >= 45 && degree <= 135 || degree >= 225 && degree <= 305)
                            return 2;
                        else return 3;
                        break;
                }
                return;


            case 3: // bedroom
                switch (object) {
                    case 1://door
                        if (degree >= 45 && degree <= 135) return 1;
                        else if (degree >= 135 && degree <= 225) return 2;
                        else return 3;
                        break;

                    case 2://window
                        if (degree >= 235 && degree <= 305) return 1;
                        else if (degree >= 135 && degree <= 225) return 2;
                        else return 3;
                        break;
                }
                return;


            default:
                return;
            break;
        }

    }

}
