function handleReady() {
    alert("Ready to go!");
}
 
// Run a named function when the document is ready.
$(document).ready(handleReady);
 
// Run an anonymous function when the document is ready.
$(document).ready(function () {
    alert("Ready to go!");
});