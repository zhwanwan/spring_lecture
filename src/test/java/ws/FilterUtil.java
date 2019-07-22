package ws;

import java.util.*;

/**
 * Explanation of the XML structure of the validation and condition filters
 * Each filterCriteriaItem is a condition
 * Each filterCriteriaRow is a grouping/parenthesis/bracket containing one or more conditions
 * Conjunction of first filterCriteriaRow and conjunction of first filterCriteriaItem in a filterCriteriaRow is always ignored. i.e. XML pieces are always stitched to the end.
 * <p>
 * Therefore, the following composite condition:
 * <p>
 * (Branch Equals to 103)
 * AND (Account Equals to 0008
 * OR Account Equals to 1120)
 * <p>
 * Is stored as:
 *
 * <filterCriteriaRow>
 * <filterCriteriaItem>
 * <attributeName>_Branch</attributeName>
 * <columnName>SEGMENT2</columnName>
 * <operator>EQUALTO</operator>
 * <conjunction>AND</conjunction> (ignored)
 * <valueDataType>STRING</valueDataType>
 * <value>103</value>
 * </filterCriteriaItem>
 * <conjunction>AND</conjunction> (ignored)
 * </filterCriteriaRow>
 * <filterCriteriaRow>
 * <filterCriteriaItem>
 * <attributeName>_Account</attributeName>
 * <columnName>SEGMENT3</columnName>
 * <operator>EQUALTO</operator>
 * <conjunction>AND</conjunction> (ignored)
 * <valueDataType>STRING</valueDataType>
 * <value>0008</value>
 * </filterCriteriaItem>
 * <filterCriteriaItem>
 * <attributeName>_Account</attributeName>
 * <columnName>SEGMENT3</columnName>
 * <operator>EQUALTO</operator>
 * <conjunction>OR</conjunction>
 * <valueDataType>STRING</valueDataType>
 * <value>1120</value>
 * </filterCriteriaItem>
 * <conjunction>AND</conjunction>
 * </filterCriteriaRow>
 * <p>
 * Whereas, the following composite condition:
 * <p>
 * (Branch Equals to 103
 * AND Account Equals to 0008)
 * OR (Account Equals to 1120)
 * <p>
 * (Branch EQUALTO 103) AND (   Account EQUALTO 0008 OR Account EQUALTO 1120 )
 * <p>
 * Is stored as:
 *
 * <filterCriteriaRow>
 * <filterCriteriaItem>
 * <attributeName>_Branch</attributeName>
 * <columnName>SEGMENT2</columnName>
 * <operator>EQUALTO</operator>
 * <conjunction>AND</conjunction>  (ignored)
 * <valueDataType>STRING</valueDataType>
 * <value>103</value>
 * </filterCriteriaItem>
 * <filterCriteriaItem>
 * <attributeName>_Account</attributeName>
 * <columnName>SEGMENT3</columnName>
 * <operator>EQUALTO</operator>
 * <conjunction>AND</conjunction>
 * <valueDataType>STRING</valueDataType>
 * <value>0008</value>
 * </filterCriteriaItem>
 * <conjunction>AND</conjunction>  (ignored)
 * </filterCriteriaRow>
 * <filterCriteriaRow>
 * <filterCriteriaItem>
 * <attributeName>_Account</attributeName>
 * <columnName>SEGMENT3</columnName>
 * <operator>EQUALTO</operator>
 * <conjunction>AND</conjunction> (ignored)
 * <valueDataType>STRING</valueDataType>
 * <value>1120</value>
 * </filterCriteriaItem>
 * <conjunction>OR</conjunction>
 * </filterCriteriaRow>
 */
public class FilterUtil {

    private static final char LBRACKET = '(';
    private static final char RBRACKET = ')';

    public static enum Operators {
        EQUALTO,
        NOTEQUALTO,
        LESSTHAN,
        GREATERTHAN,
        LESSTHANEQUALTO,
        GREATERTHANEQUALTO,
        BETWEEN,
        NOTBETWEEN,
        CUSTOM,
        LIKE,
        STARTSWITH,
        ENDSWITH,
        ISNULL,
        ISNOTNULL,
        CONTAINS,
        DOESNOTCONTAIN;
    }

    public static enum Conjunction {
        AND,
        OR;
    }

    public static enum ValueDataType {
        STRING,
        NUMBER,
        DATE;
    }

    /**
     * Checks if the attribute's value is null.
     * Throws an IllegalArgumentException if the value is null.
     *
     * @param name  attributeName
     * @param value
     */
    private static void validateNotNullArg(String name, Object value) {
        if (value == null) {
            String errorMsg = name + " argument cannot be null.";
            /**
             *       if (AppsLogger.isEnabled(AppsLogger.SEVERE)) {
             *         AppsLogger.write(FilterUtil.class, errorMsg, AppsLogger.SEVERE);
             *       }
             */
            throw new IllegalArgumentException(errorMsg);

        }
    }

    /**
     * Checks if the brackets of filterText are matched.
     * Throws an IllegalArgumentException if brackets unmatched.
     * Example:
     * Validated:   (Branch EQUALTO 103 AND Account EQUALTO 0008)
     * Invalidated: (Branch EQUALTO 103 AND Account EQUALTO 0008
     * <p>
     * (A EQ 10 OR (A EQ 11 AND B EQ 10))
     *
     * @param filterText
     */
    private static void validateFilterText(String filterText) {
        //validateNotNullArg("filterText", filterText);
        String errorMsg = "Brackets are not matched: " + filterText;
        Deque<Character> deque = new LinkedList<Character>();
        //filterText = filterText.trim();

        char c;
        boolean isMatched = true;

        for (int i = 0, len = filterText.length(); i < len; i++) {
            c = filterText.charAt(i);
            if (FilterUtil.LBRACKET == c) {
                deque.push(c);
            } else if (FilterUtil.RBRACKET == c) {
                if (deque.isEmpty()) {
                    isMatched = false;
                    break;
                } else {
                    deque.pop();
                }
            }
        }

        if (!deque.isEmpty() || !isMatched) {
            throw new IllegalArgumentException(errorMsg);
        }
    }

    /*private static List<KeyFlexFilterCriteriaItem> getFilterCriteriaItems(String filterText) {
        List<KeyFlexFilterCriteriaItem> criteriaItems = new ArrayList<KeyFlexFilterCriteriaItem>();
        if (filterText != null) {
            StringTokenizer filterPartialTokenizer = new StringTokenizer(filterText, ",");

            while (filterPartialTokenizer.hasMoreElements()) {
                String filterPartialText = filterPartialTokenizer.nextElement().toString().trim();
                StringTokenizer filterTokenizer = new StringTokenizer(filterPartialText, " ");

                String segCode = null;
                String operator = null;
                String operator_value = null;
                while (filterTokenizer.hasMoreElements()) {

                    segCode = filterTokenizer.nextElement().toString().trim();
                    operator = filterTokenizer.nextElement().toString().trim();
                    KeyFlexFilterCriteriaItem criteriaItem = new KeyFlexFilterCriteriaItem();
                    criteriaItem.setSegmentCode(segCode);
                    criteriaItem.setOperator(operator);
                    if (FilterUtil.Operators.BETWEEN.toString().equals(FilterUtil.getEnumOperator(operator)) ||
                            FilterUtil.Operators.NOTBETWEEN.toString().equals(FilterUtil.getEnumOperator(operator))) {

                        operator_value = filterTokenizer.nextElement().toString().trim();
                        StringTokenizer betweenTokenizer =
                                new StringTokenizer(operator_value, "-");

                        String lValue = null;
                        String rValue = null;
                        if (betweenTokenizer.hasMoreElements()) {
                            lValue = betweenTokenizer.nextElement().toString().trim();
                            rValue = betweenTokenizer.nextElement().toString().trim();
                            criteriaItem.setValues(lValue, rValue);
                        }

                    }*//* else if (FilterUtil.Operators.ISNULL.toString().equals(FilterUtil.getEnumOperator(operator)) ||
                            FilterUtil.Operators.ISNOTNULL.toString().equals(FilterUtil.getEnumOperator(operator))) {
                    }*//* else {
                        operator_value = filterTokenizer.nextElement().toString().trim();
                        criteriaItem.setValues(operator_value);
                    }
                    criteriaItems.add(criteriaItem);
                }
            }
        }
        return criteriaItems;
    }*/

    /**
     * (Branch EQUALTO 103) AND (Account EQUALTO 0008 OR Account EQUALTO 1120)
     */
    public static void main(String[] args) {
        String filterText = "(Branch EQUALTO 103)\n" +
                "AND (   Account EQUALTO 0008\n" +
                "     OR Account EQUALTO 1120)";
        validateFilterText(filterText);

        filterText = "(Branch   EQUALTO   103) AND (Account    EQUALTO 0008 OR    Account EQUALTO 1120)";
        System.out.println(filterText);
        filterText = filterText.replaceAll("\\(", "( ").replaceAll("\\)", " )");
        System.out.println(filterText);
        StringTokenizer tokenizer = new StringTokenizer(filterText, " ");
        while (tokenizer.hasMoreElements()) {
            System.out.print(tokenizer.nextElement() + " ");
        }


    }
}
