package Day4;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SecureContainer {

    public static void main(String[] args) {
        SecureContainer secureContainer = new SecureContainer();
//        System.out.println(secureContainer.validatePasswords(171309, 171310));
        System.out.println(secureContainer.validatePasswords(171309, 643603));
    }

    public int validatePasswords(int start, int end) {
        List<Integer> goodPasswords = new ArrayList<>();
        password: for (int i = start; i <= end; i++) {
            boolean hasDouble = false;
            boolean hasTribbel = false;
            String password = String.valueOf(i);
            int lastDigit = 999999999;
            int lastLastDigit = 999999999;
            List<Integer> doubleDigit = new ArrayList<>();
            List<Integer> trippelDigit = new ArrayList<>();
            for (int i1 = 0; i1 < password.length(); i1++) {
                System.out.println(i1);
                int currentDigit = Integer.parseInt(password.substring(i1, i1+1));
                if (i1 > 1) {
                    lastLastDigit = Integer.parseInt(password.substring(i1-2, i1-1));
                }
                if (lastDigit == 999999999) {
                    lastDigit = currentDigit;
                    continue;
                }
                if (i1 > 0) {
                    lastDigit = Integer.parseInt(password.substring(i1-1, i1));
                }
                if (currentDigit >= lastDigit) {
                    if (lastDigit == currentDigit) {
                        hasDouble = true;
                        doubleDigit.add(currentDigit);
                    }
                    if (lastLastDigit != 999999999 && (lastLastDigit == lastDigit && lastDigit == currentDigit)) {
                        hasTribbel = true;
                        trippelDigit.add(currentDigit);
                    }
                } else {
                    continue password;
                }
            }
            int sizeOfDouble = doubleDigit.stream().filter(integer -> !trippelDigit.contains(integer)).collect(Collectors.toList()).size();
            if (hasDouble && sizeOfDouble > 0) {
                goodPasswords.add(i);
            }
        }
        return goodPasswords.size();
    }
}
