class RatedGroceryProduct extends GroceryProduct implements RatedProduct {
    private double rating;
    private String userFeedback = "";

    public RatedGroceryProduct(String name, double price, int quantityInStock) {
        super(name, price, quantityInStock);
        this.rating = 0.0;
    }

    @Override
    public double getRating() {
        return rating;
    }

    @Override
    public void rateProduct(double rating) {
        if (rating >= 0.0 && rating <= 5.0) {
            this.rating = rating;
            System.out.println("Product rated: " + rating + " stars");
        } else {
            System.out.println("Invalid rating. Please provide a rating between 0 and 5 stars.");
        }
    }

    // Additional method specific to this class
    public void suggestImprovements(String feedback) {
        this.userFeedback = feedback;
        System.out.println("Thank you for your feedback on " + name + ": " + userFeedback);
    }
}
