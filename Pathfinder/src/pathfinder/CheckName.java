
package pathfinder;

public class CheckName {

    public boolean isValidName(String name) {
        for (char c : name.toCharArray()) {
            if (!Character.isLetter(c) & c != ' ') {

                return false;
            }

        }
        if (name.isEmpty()) {
            return false;
        }
        return true;
    }

}
