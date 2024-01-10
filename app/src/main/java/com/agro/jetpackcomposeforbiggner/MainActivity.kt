package com.agro.jetpackcomposeforbiggner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.agro.jetpackcomposeforbiggner.ui.theme.JetpackComposeForBiggnerTheme
import com.agro.jetpackcomposeforbiggner.ui.utils.FILTER_CONTENT_LIST
import com.agro.jetpackcomposeforbiggner.ui.utils.FilterContent
import com.agro.jetpackcomposeforbiggner.ui.utils.MEDITATION_TYPE_LIST
import com.agro.jetpackcomposeforbiggner.ui.utils.MeditationType


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeForBiggnerTheme {
                // A surface container using the 'background' color from the theme
                    Column(
                        modifier = Modifier
                            .background(Color.LightGray)
                            .fillMaxSize()
                    ){
                        HeaderProfileComponent()
                        SearchInputComponent()
                        FilterOptionsComponent()
                        MeditationTypesComponent()
                    }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Preview
    @Composable
    fun HeaderProfileComponent(){
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp, top = 15.dp)
        ){
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.profilepicture),
                    contentDescription = "Profile picture",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                )
                Column(modifier = Modifier.padding(start = 10.dp)) {
                    Text(
                        text = "Welcome back",
                        fontFamily = FontFamily.Serif,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = "Sona Darling",
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Start
                    )
                }
            }
            BadgedBox(badge = { Badge(Modifier.background(Green)) }) {
                Icon(
                    Icons.Default.Notifications,
                    contentDescription = "Notifications"
                )
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Preview
    @Composable
    fun SearchInputComponent(){
        OutlinedTextField(
            value = "", onValueChange = {},
            placeholder = { Text(text = "Search", fontFamily = FontFamily.SansSerif) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon"
                )
            },
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.filter),
                    modifier = Modifier.size(24.dp),
                    contentDescription = "Filter Icon"
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 25.dp, start = 15.dp, end = 15.dp)
                .background(color = Color.White, shape = RoundedCornerShape(8.dp)),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.LightGray,
                unfocusedBorderColor = Color.White,
                cursorColor = Color.LightGray,
                focusedTrailingIconColor = Black
            )
        )
    }
    @Composable
    fun FilterOptionsComponent() {
        val filterOptions = FILTER_CONTENT_LIST
        LazyRow(
            Modifier.padding(top = 15.dp, start = 15.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(filterOptions.size) {
                ChipComponent(filter = filterOptions[it])
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ChipComponent(filter: FilterContent){
        val contentColor = filter.contentColor
        val chipBackground = filter.backgroundColor
        val filterText = filter.filterText
        var selected by remember { mutableStateOf(false) }

        FilterChip(
            onClick = { selected = !selected },
            label = {
                Text(text = filterText, fontFamily = FontFamily.SansSerif)
            },
            selected = selected,
            leadingIcon = if (selected) {
                {
                    Icon(
                        imageVector = Icons.Filled.Done,
                        contentDescription = "Done icon",
                        modifier = Modifier.size(FilterChipDefaults.IconSize)
                    )
                }
            } else {
                null
            },
            colors = FilterChipDefaults.filterChipColors(
                chipBackground,contentColor
            )
        )

    }
    @Composable
    fun MeditationTypesComponent() {
        val meditationOptions = MEDITATION_TYPE_LIST
        LazyColumn(
            Modifier.padding(15.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            items(meditationOptions.size) {
                MeditationOptionComponent(meditationTypes = meditationOptions[it])
            }
        }
    }
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MeditationOptionComponent(meditationTypes: MeditationType) {
        val contextForToast = LocalContext.current.applicationContext
        var selected by remember { mutableStateOf(false) }

        Card(
            shape = RoundedCornerShape(14.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(20.dp)
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    FilterChip(
                        modifier = Modifier.padding(horizontal = 6.dp), // gap between items
                        selected = selected,
                        onClick = {

                        },
                        label = {
                            Text(text = meditationTypes.duration)
                        }
                    )

                    FilterChip(
                        onClick = { /*TODO*/ },
                        colors =FilterChipDefaults.filterChipColors(
                            labelColor = Black,
                            containerColor = Color.White
                        ),
                        shape = RoundedCornerShape(8.dp),
                        selected = selected,
                        label = {
                            Text(text = meditationTypes.teacher, fontFamily = FontFamily.SansSerif)
                        }
                    )
                }

                Text(
                    text = meditationTypes.title,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 18.sp,
                    color = meditationTypes.contentColor,
                    textAlign = TextAlign.Start
                )

                Text(
                    text = meditationTypes.description,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 16.sp,
                    color = meditationTypes.contentColor,
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}

