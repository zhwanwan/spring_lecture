package ws;


/**
 * Data Structure used to capture individual FilterCriteriaItem
 *
 * This dataStrucutre can be used in the following ways to populate various conditions -
 *
 *   KeyFlexFilterCriteriaItem item1 = new KeyFlexFilterCriteriaItem();
 *   item1.setSegmentCode("L10");
 *   item1.setOperator("=");
 *   String[] values = {"1"};
 *   item1.setValues(values);
 *   This is equivalent to - 'ColumnName(L10) = 1'
 *
 *   KeyFlexFilterCriteriaItem item2 = new KeyFlexFilterCriteriaItem();
 *   item2.setSegmentCode("L10_NUMONLY");
 *   item2.setOperator("BETWEEN");
 *   String[] values2 = {"99", "199"};
 *   item2.setValues(values2);
 *   This is equivalent to - 'ColumnName(L10_NUMONLY) BETWEEN 99 and 199'
 *
 *   KeyFlexFilterCriteriaItem item3 = new KeyFlexFilterCriteriaItem();
 *   item3.setSegmentCode("L10_ZEROFILL");
 *   item3.setOperator("IS_DESCENDENT_OF");
 *   String[] values3 = {"199"};
 *   item3.setValues(values3);
 *   This is equivalent to - ' ColumnName(L10_ZEROFILL) IS_DESCENDENT_OF 199'
 *
 *
 *
 * Rules to followed while creating the KeyFlexFilterCriteriaItems -
 *
 *   For opertaors 'ISBLANK' and 'ISNOTBLANK' variable 'values' should be null
 *   For operators 'BETWEEN' and 'NOTBETWEEN' variable 'values' should have 2 values
 *   For other operators variable 'values' should have only 1 value
 *   This class will not validate any of the inputs. However when you pass the
 *   list of KeyFlexFilterCriteriaItem to createFilterXML then you will get
 *   IllegalArgumentException incase the rules are not followed
 *
 *   Variable 'conjunction' is used while grouping together same 'segmentCode'
 *
 *   Different 'segmentCode' are always ANDed
 *   Same 'segmentCode' are always ORed
 *
 *
 */
public class KeyFlexFilterCriteriaItem {
    private String segmentCode;
    private String operator;
    private String[] values;

    public KeyFlexFilterCriteriaItem() {
        super();
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }

    public void setValues(String... values) {
        if (values == null) {
            this.values = null;
        } else {
            String[] r = new String[values.length];
            System.arraycopy(values, 0, r, 0, values.length);
            this.values = r;
        }
    }

    public String[] getValues() {
        if (values == null) {
            return null;
        }
        String[] r = new String[values.length];
        System.arraycopy(values, 0, r, 0, values.length);
        return r;
    }

    public void setSegmentCode(String segmentCode) {
        this.segmentCode = segmentCode;
    }

    public String getSegmentCode() {
        return segmentCode;
    }
}