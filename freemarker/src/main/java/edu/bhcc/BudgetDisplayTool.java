package edu.bhcc;

import com.google.common.base.Strings;
import edu.bhcc.model.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Budget Display Tool.
 * @author James Chan
 */
public class BudgetDisplayTool {
    public static void main(String[] args) throws Exception {
        final var transactionService = new TransactionService();
        final List<Transaction> transactionList = transactionService.getAllTransaction();

        final List<List<String>> content = new ArrayList<>();
        final List<String> header = List.of("Category", "Assigned", "Activity", "Remaining");

        transactionList.forEach(transaction -> {
            final long assigned = transaction.getCategory().getAllocated();
            final long amount = transaction.getAmount();

            content.add(List.of(
                transaction.getCategory().getName(),
                "$" + (double) assigned / 100,
                "$" + (double) amount / 100,
                "$" + (double) (assigned - amount) / 100
            ));
        });

        displayOnTerminal(header, content);

        // Force exit the program.
        System.exit(0);
    }

    /**
     * Displays a table on terminal.
     * @param header  the header of the table.
     * @param content the content of the table.
     */
    private static void displayOnTerminal(List<String> header, List<List<String>> content) {
        final StringBuilder stringBuilder = new StringBuilder();
        final List<Integer> maxColumnLengthList = new ArrayList<>();

        header.forEach(item -> maxColumnLengthList.add(item.length()));
        for (final var line : content) {
            for (int i = 0; i < header.size(); i++) {
                final int len = line.get(i).length();
                if (len > maxColumnLengthList.get(i)) {
                    maxColumnLengthList.set(i, len);
                }
            }
        }

        final int fullLength =
            maxColumnLengthList.stream().reduce(Integer::sum).orElse(1) + header.size() * 3 + 1;

        // header
        stringBuilder.append("=".repeat(fullLength)).append("\n").append("|");
        for (int i = 0; i < header.size(); i++) {
            stringBuilder.append(" ")
                .append(Strings.padEnd(header.get(i), maxColumnLengthList.get(i), ' '))
                .append(' ')
                .append('|');
        }
        stringBuilder.append("\n");

        // the divider between header and content is a little bit different
        stringBuilder.append("+");
        for (int i = 0; i < header.size(); i++) {
            stringBuilder.append("=".repeat(maxColumnLengthList.get(i) + 2)).append("+");
        }
        stringBuilder.append("\n");

        // content
        for (final List<String> line : content) {
            stringBuilder.append("|");
            for (int j = 0; j < line.size(); j++) {
                stringBuilder.append(" ")
                    .append(Strings.padEnd(line.get(j), maxColumnLengthList.get(j), ' '))
                    .append(' ')
                    .append('|');
            }
            stringBuilder.append("\n");
        }
        stringBuilder.append("=".repeat(fullLength));

        System.out.println(stringBuilder);
    }
}
