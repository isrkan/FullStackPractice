// Script 1: Select the header element and change its text content
const header = document.querySelector('.header-title'); // Select the header title using its class name and store it in a variable
header.textContent = 'Discover Our Exciting Products'; // Change the text content of the header


// Script 2: Select the navigation links and add event listeners to log a message when clicked
const navigationLinks = document.querySelectorAll('.navigation-link'); // Select all navigation links using their class name and store them in a variable
navigationLinks.forEach(link => { // Loop through each navigation link
    // Add click event listener to each navigation link
    link.addEventListener('click', () => {
        // Log a message when a navigation link is clicked
        console.log(`Clicked on ${link.textContent}`);
    });
});


// Script 3: Select the 'Add to Cart' buttons and add event listeners to alert a message when clicked
const addToCartButtons = document.querySelectorAll('.add-to-cart');
addToCartButtons.forEach(button => {
    button.addEventListener('click', () => {
        // Alert a message when the button is clicked
        alert('Item added to cart!');
        // Change button text and background color temporarily
        button.textContent = 'Added!';
        button.style.backgroundColor = '#5cb85c';
        setTimeout(() => { 
            // Restore button text and background color after 2 seconds
            button.textContent = 'Add to Cart';
            button.style.backgroundColor = '#333';
        }, 2000);
        console.log('Item added to cart');
    });
});


// Script 4: Select the special-offer content area and change its background color randomly every few seconds
const mainContent = document.querySelector('.special-offer');
let randomColorInterval;

function changeBackgroundColor() {
    // Generate a random color
    const randomColor = '#' + Math.floor(Math.random() * 16777215).toString(16);
    // Apply transition effect and change background color
    mainContent.style.transition = 'background-color 0.5s ease'; // Apply a transition effect to smoothly change the background color
    mainContent.style.backgroundColor = randomColor;
}

function startRandomColorInterval() {
    // Start interval to change background color every 5 seconds
    randomColorInterval = setInterval(changeBackgroundColor, 5000);
}

function stopRandomColorInterval() {
    // Stop interval when content area is out of view
    clearInterval(randomColorInterval);
}

// Start the interval when the content area is in view
window.addEventListener('load', startRandomColorInterval);
// Stop the interval when the content area is out of view
window.addEventListener('unload', stopRandomColorInterval);


// Script 5: Select the product comparison table and change the font color of its cells
const comparisonTable = document.querySelector('table');
const tableCells = comparisonTable.querySelectorAll('td');
tableCells.forEach(cell => {
    // Change font color of table cells
    cell.style.color = '#444';
});


// Script 6: Select the aside element and append a new paragraph with a promotional message
const specialOfferSection = document.querySelector('.special-offer');
const promoMessage = document.createElement('p');
promoMessage.textContent = 'Hurry! Special offer ending soon.';
promoMessage.style.fontWeight = 'bold';
promoMessage.style.color = '#cc3300';
specialOfferSection.appendChild(promoMessage); // Append the message paragraph to the special offer section


// Script 7: Select the header title and change its font size when hovered over
const headerTitle = document.querySelector('.header-title');
headerTitle.addEventListener('mouseenter', () => { // mouseenter: Triggered when the mouse enters an element's boundary

    // Apply transition effect and change font size
    headerTitle.style.transition = 'transform 0.3s ease';
    headerTitle.style.transform = 'scale(1.03)';
    console.log('Hovered over header title');
});
headerTitle.addEventListener('mouseleave', () => { // mouseleave: Triggered when the mouse leaves an element's boundary
    // Restore original font size when mouse leaves
    headerTitle.style.transform = 'scale(1)';
});


// Script 8: Select the contact link and log a message when hovered over
const contactLink = document.querySelector('.contact-link');
contactLink.addEventListener('mouseover', () => { // mouseover: Triggered when the mouse moves over an element, including when it moves over its children
    console.log('Hovered over contact link');
});


// Script 9: Select the footer and change its background color when the mouse moves over it
const footer = document.querySelector('footer');
footer.addEventListener('mouseenter', () => {
    footer.style.transition = 'background-color 0.3s ease';
    footer.style.backgroundColor = '#ccc';
});
footer.addEventListener('mouseleave', () => {
    footer.style.transition = 'background-color 0.3s ease';
    footer.style.backgroundColor = '#555';
});


// Script 10: Append a disclaimer to the footer
const disclaimer = document.createElement('p');
disclaimer.textContent = 'Prices are subject to change without notice.';
footer.appendChild(disclaimer);