//  OutsourcedModel.java
//  C482
//  Created by AbdAllah M AbdAllah Khodeir
// Student ID: 010734215

package C482.Model;

/**
 *
 * @author AbdAllah Khodeir
 */

/**
 * Setting the OutsourceModel Class.
 */
public class OutsourcedModel extends PartModel {
    private String companyName;

    public OutsourcedModel(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * Company Name getter.
     *
     */
    public String getCompanyName() {

        return companyName;
    }

    /**
     * Company Name setter.
     */
    public void setCompanyName(String companyName) {

        this.companyName = companyName;
    }
}