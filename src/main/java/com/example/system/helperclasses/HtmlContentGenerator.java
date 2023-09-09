package com.example.system.helperclasses;


import com.example.system.models.Rule;
import com.example.system.models.Transaction;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
public class HtmlContentGenerator {

    public static String generateTransactionTable(List<Transaction> transactions) {
        StringBuilder table = new StringBuilder("<table border=\"1\">");
        table.append("<tr><th>Transaction ID</th><th>Payment Type</th><th>Plate Number</th><th>Entry Time</th><th>Estimated Exit Time</th><th>Value</th><th>Status</th></tr>");

        for (Transaction transaction : transactions) {
            table.append("<tr>");
            table.append("<td>").append(transaction.getTransactionId()).append("</td>");
            table.append("<td>").append(transaction.getTransactionPaymentType()).append("</td>");
            table.append("<td>").append(transaction.getPlateNumber()).append("</td>");
            table.append("<td>").append(transaction.getEntryTime()).append("</td>");
            table.append("<td>").append(transaction.getEstimatedExitTime()).append("</td>");
            table.append("<td>").append(transaction.getTransactionValue()).append("</td>");
            table.append("<td>").append(transaction.getTransactionStatus()).append("</td>");
            table.append("</tr>");
        }

        table.append("</table>");
        return table.toString();
    }

    public static String generateRulesTable(List<Rule> rules) {
        StringBuilder table = new StringBuilder("<table border=\"1\">");
        table.append("<tr><th>Rule ID</th><th>Name</th><th>Details</th><th>Cost</th><th>Start Time</th><th>End Time</th></tr>");

        for (Rule rule : rules) {
            table.append("<tr>");
            table.append("<td>").append(rule.getRuleId()).append("</td>");
            table.append("<td>").append(rule.getName()).append("</td>");
            table.append("<td>").append(rule.getDetails()).append("</td>");
            table.append("<td>").append(rule.getCost()).append("</td>");
            table.append("<td>").append(rule.getStartTime()).append("</td>");
            table.append("<td>").append(rule.getEndTime()).append("</td>");
            table.append("</tr>");
        }

        table.append("</table>");
        return table.toString();
    }
}

