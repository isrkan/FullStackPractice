from datetime import datetime
from food_product import FoodProduct
from electronic_product import ElectronicProduct
from clothing_product import ClothingProduct

def main():
    # Creating objects of the subclasses
    expirationDate = datetime(2023, 12, 31, 12, 0)  # Example expiration date
    pasta = FoodProduct(1, "Pasta", 1.99, "Osem", expirationDate, True)
    laptop = ElectronicProduct(2, "Mac", 899.99, "HP", "Pavilion", "x360s")
    shirt = ClothingProduct(3, "Shirt", 19.99, "Zara", "Medium", "Cotton")

    # Displaying information
    print("Food product information:")
    pasta.displayInfo()
    print("\nElectronic product information:")
    laptop.displayInfo()
    print("\nClothing product information:")
    shirt.displayInfo()

if __name__ == "__main__":
    main()