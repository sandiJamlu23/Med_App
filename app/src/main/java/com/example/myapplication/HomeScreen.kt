package com.example.myapplication

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.AquaBlue
import com.example.myapplication.ui.theme.BlueViolet1
import com.example.myapplication.ui.theme.BlueViolet2
import com.example.myapplication.ui.theme.BlueViolet3
import com.example.myapplication.ui.theme.ButtonBlue
import com.example.myapplication.ui.theme.DarkerButtonBlue
import com.example.myapplication.ui.theme.DeepBlue
import com.example.myapplication.ui.theme.TextWhite

@ExperimentalFoundationApi
@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        Column{
            greetSection()
            apalahSection(chips = listOf("Sweet Sleep", "Insomnia", "Depression"))
            CurrentMeditation()
            FeatureSection(
                features = listOf(
                    Feature(
                        title = "Sleep meditation",
                        R.drawable.ic_headphone,
                        BlueViolet1,
                        BlueViolet2,
                        BlueViolet3
                    ),
                    Feature(
                        title = "Tips",
                        R.drawable.ic_video,
                        BlueViolet1,
                        BlueViolet2,
                        BlueViolet3
                     ),
                    Feature(
                        title = "Night",
                        R.drawable.ic_moon,
                        BlueViolet1,
                        BlueViolet2,
                        BlueViolet3
                    ),
                    Feature(
                        title = "Calm",
                        R.drawable.ic_music,
                        BlueViolet1,
                        BlueViolet2,
                        BlueViolet3
                    )
                )
            )
        }
        BottomMenu(items = listOf(
            BottomMenuContent("Home", R.drawable.ic_moon),
            BottomMenuContent("Medicate", R.drawable.ic_music),
            BottomMenuContent("Sleep", R.drawable.ic_moon),
            BottomMenuContent("Music", R.drawable.ic_music),
            BottomMenuContent("Profile", R.drawable.ic_moon)
        ),
           modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}
@Composable
fun greetSection(
    name: String = "I am Groot"
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ){
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Good Morning, $name",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "UNTO",
                style = MaterialTheme.typography.bodySmall
            )
        }
        Icon(painter = painterResource(id = R.drawable.ic_search),
            contentDescription = "Search",
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun apalahSection(chips: List<String>)
{
    var selectedChipIndex by remember{
        mutableStateOf(0)
    }
    LazyRow {
        items(chips.size){
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(start=15.dp, top=15.dp,bottom=5.dp)
                    .clickable {
                        selectedChipIndex = it
                    }
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        if (selectedChipIndex == it) ButtonBlue else DarkerButtonBlue
                    )
                    .padding(15.dp)
            )
            {
                Text(text = chips[it], color = TextWhite)
            }
        }
    }
}
@Composable
fun BottomMenu(items: List<BottomMenuContent>,
               modifier: Modifier = Modifier,
               highLightColor: Color = ButtonBlue,
               activeTextColor: Color = Color.White,
               inactiveTextColor: Color = AquaBlue,
               initialSelectedItemIndex: Int = 0 //reflect the change of selected item
){
    var selectedItemIndex by remember {
        mutableStateOf(initialSelectedItemIndex)
    }
    Row( // This creates a horizontal layout that arranges its children in a row.
        horizontalArrangement = Arrangement.SpaceAround, // Distributes the children evenly across the horizontal axis, with space around each child.
        verticalAlignment = Alignment.CenterVertically, // Aligns the children vertically, centering them along the vertical axis.
        modifier = modifier // Applies the modifier passed to the Row, allowing for additional modifications.
            .fillMaxWidth() // Makes the Row take up the full width of its parent container.
            .background(DeepBlue) // Sets the background color of the Row to DeepBlue.
            .padding(15.dp) // Adds padding of 15 density-independent pixels (dp) around the Row's content.
    ){
        items.forEachIndexed { index, item -> // Iterates over the list of items, providing both the index and the item for each element.
            BottomMenuItem( // Composable function that represents an individual item in the bottom menu.
                item = item, // Passes the current item to the BottomMenuItem.
                isSelected = index == selectedItemIndex, // Checks if the current index matches the selected item index, and sets the item's selected state accordingly.
                activeTextColor = activeTextColor, // Sets the text color for the selected item.
                inactiveTextColor = inactiveTextColor, // Sets the text color for the unselected items.
            ) {
                selectedItemIndex = index // Updates the selected item index when this item is clicked, triggering a UI update.
            }
        }


    }

}

@Composable
fun BottomMenuItem(
    item: BottomMenuContent,
    isSelected: Boolean,
    highLightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    onItemClick: () -> Unit
){
    Column( // Creates a vertical layout that arranges its children in a column.
        horizontalAlignment = Alignment.CenterHorizontally, // Centers the children horizontally within the Column.
        verticalArrangement = Arrangement.Center, // Centers the children vertically within the Column.
        modifier = Modifier.clickable { // Adds a clickable modifier to the Column, making it respond to clicks.
            onItemClick() // Calls the onItemClick function when the Column is clicked.
        }
    ) {
        Box( // Creates a container that allows for stacking or overlaying content.
            contentAlignment = Alignment.Center, // Centers the content within the Box both horizontally and vertically.
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp)) // Clips the Box's corners to create a rounded shape with a radius of 10 dp.
                .background(if (isSelected) highLightColor else Color.Transparent) // Sets the background color based on whether the item is selected or not.
                .padding(10.dp) // Adds padding of 10 dp inside the Box around its content.
        ) {
            Icon( // Displays an icon within the Box.
                painter = painterResource(id = item.iconId), // Loads the icon resource using the item's iconId.
                contentDescription = item.title, // Provides a description for the icon, often used for accessibility.
                tint = if (isSelected) activeTextColor else inactiveTextColor, // Sets the icon color based on whether the item is selected or not.
                modifier = Modifier.size(20.dp) // Sets the size of the icon to 20 dp.
            )
        }
        Text(
            text = item.title,
            color = if (isSelected) activeTextColor else inactiveTextColor
        )
    }
}

fun onItemClick() {
    TODO("Not yet implemented")
}



@Composable
fun FeatureSection(features: List<Feature>) {

}

@Composable
fun CurrentMeditation() {
    TODO("Not yet implemented")
}


