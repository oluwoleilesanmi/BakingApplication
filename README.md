# Baking Application

-  Design and Implement a production-ready Android Application.

## Rubric

### General App Usage
- App should display recipes from provided network resource.
- App should allow navigation between individual recipes and recipe steps.
- App uses RecyclerView and can handle recipe steps that include videos or images.
- App conforms to common standards found in the Android Nanodegree General Project Guidelines.

### Components and Libraries
- Application uses Master Detail Flow to display recipe steps and navigation between them.
- Application uses Exo-player to display videos.
- Application properly initializes and releases video assets when appropriate.
- Application should properly retrieve media assets from the provided network links. It should properly handle network requests.
- Application makes use of Espresso to test aspects of the UI.
- Application sensibly utilizes a third-party library to enhance the app's features. That could be helper library to interface with Content Providers if you choose to store the recipes, a UI binding library to avoid writing findViewById a bunch of times, or something similar.

### Homescreen Widget
- Application has a companion homescreen widget.
- Widget displays ingredient list for desired recipe.