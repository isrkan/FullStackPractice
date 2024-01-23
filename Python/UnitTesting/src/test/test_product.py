import pytest
from main.product import Product

class TestProduct:

    # Test case for checking the correct calculation of discounted price
    def test_apply_discount(self):
        # Arrange: Create a product with an initial price
        product = Product("Laptop", 1000)
        # Act: Apply a discount
        product.apply_discount(10)
        # Assert: Check if the discounted price is calculated correctly
        assert product.get_price() == 900

    # Parameterized test for applying discounts with different percentages
    @pytest.mark.parametrize("discount_percentage, expected_price", [(10, 900), (20, 800), (0, 1000), (50, 500)])
    def test_apply_discount_with_different_percentages(self, discount_percentage, expected_price):
        # Arrange: Create a product with an initial price
        product = Product("Test product", 1000)
        # Act: Apply a discount with the specified percentage
        product.apply_discount(discount_percentage)
        # Assert: Check if the discounted price is calculated correctly
        assert product.get_price() == expected_price

    # Test case for checking the correct handling of invalid discount percentage
    def test_apply_invalid_discount(self):
        # Arrange: Create a product with an initial price
        product = Product("Smartphone", 500)
        # Act & assert: Applying an invalid discount should raise an exception
        with pytest.raises(ValueError):
            product.apply_discount(-5)
        with pytest.raises(ValueError):
            product.apply_discount(110)

    # Test case for checking the correct modification of product name
    def test_modify_product_name(self):
        # Arrange: Create a product with an initial name
        product = Product("Tablet", 300)
        # Act: Modify the product name
        product.set_name("New Tablet")
        # Assert: Check if the name is modified correctly
        assert product.get_name() == "New Tablet"

    # Parameterized test for modifying product names
    @pytest.mark.parametrize("initial_name, modified_name", [("Desktop", "Desktop pro"), ("Printer", "3D Printer")])
    def test_modify_product_names(self, initial_name, modified_name):
        # Arrange: Create a product with an initial name
        product = Product(initial_name, 400)
        # Act: Modify the product name
        product.set_name(modified_name)
        # Assert: Check if the name is modified correctly
        assert product.get_name() == modified_name