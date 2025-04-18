package budgetbuddy.parser;

import budgetbuddy.exception.InvalidInputException;

/**
 * Parses the "edit-budget" command to extract current name, new amount, and new name.
 * Supports optional parameters: a/, c/
 */
public class EditBudgetParser extends Parser<String[]> {
    private static final double MAX_AMOUNT = 100000; // Define a maximum amount limit

    public EditBudgetParser(String input) {
        super(input);
    }

    @Override
    public String[] parse() throws InvalidInputException {
        String[] result = {"", "", ""};
        if (!input.startsWith("edit-budget ")) {
            throw new InvalidInputException("Use: edit-budget old/<Name> a/<Amount> c/<NewName>");
        }
        String line = input.substring("edit-budget ".length()).trim();
        if (!line.contains("old/")) {
            throw new InvalidInputException("Missing old/ prefix.");
        }
        String[] splitOld = line.split("old/", 2);
        String[] currentNameString = splitOld[1].trim().split(" ");
        result[0] = currentNameString[0].trim();
        String remaining = splitOld[1].substring(result[0].length()).trim();
        if (remaining.contains("a/")) {
            String amt = remaining.split("a/", 2)[1].split(" ")[0].trim();
            try {
                double amount = Double.parseDouble(amt);
                if (amount < 0 || amount > MAX_AMOUNT) {
                    throw new InvalidInputException("Amount must be between 0 and " + MAX_AMOUNT);
                }
                result[1] = amt;
            } catch (NumberFormatException e) {
                throw new InvalidInputException("Invalid amount format");
            }
        }
        if (remaining.contains("c/")) {
            String cat = remaining.split("c/", 2)[1].split(" ")[0].trim();
            if (cat.isEmpty()) {
                throw new InvalidInputException("Budget name cannot be empty.");
            }
            result[2] = cat;
        }
        if (result[1].isEmpty() && result[2].isEmpty()) {
            throw new InvalidInputException("Must specify at least one of a/ or c/");
        }
        return result;
    }
}
