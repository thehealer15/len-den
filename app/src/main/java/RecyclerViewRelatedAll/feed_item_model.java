package RecyclerViewRelatedAll;

public class feed_item_model {
    String credit_score,amount, tenure, interest_rate;

    public feed_item_model(){credit_score = amount = tenure = interest_rate = ""; }
    public feed_item_model(String credit_score, String amount, String tenure, String interest_rate) {
        this.credit_score = credit_score;
        this.amount = amount;
        this.tenure = tenure;
        this.interest_rate = interest_rate;
    }

    public String getCredit_score() {
        return credit_score;
    }

    public void setCredit_score(String credit_score) {
        this.credit_score = credit_score;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTenure() {
        return tenure;
    }

    public void setTenure(String tenure) {
        this.tenure = tenure;
    }

    public String getInterest_rate() {
        return interest_rate;
    }

    public void setInterest_rate(String interest_rate) {
        this.interest_rate = interest_rate;
    }
}
