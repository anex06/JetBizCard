package com.myjetpack.jetbizcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myjetpack.jetbizcard.ui.theme.JetBizCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetBizCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                  CreateBizCard()
                }
            }
        }
    }
}

@Composable
fun CreateBizCard(){
    val buttonClickedState = remember {
        mutableStateOf(false)
    }
  Surface(modifier = Modifier
      .fillMaxWidth()
      .fillMaxHeight()) {
      Card(modifier = Modifier
          .width(width = 200.dp)
          .height(height = 300.dp)
          .padding(all = 12.dp),
          elevation = 4.dp,
      shape = RoundedCornerShape(corner = CornerSize(size = 15.dp)),

      ) {
          Column(modifier = Modifier.size(300.dp),
          verticalArrangement = Arrangement.Top,
          horizontalAlignment = Alignment.CenterHorizontally) {
              CreateImageProfile()
              Divider()
              CreateProfileInfo("Anup", "Mobile app developer", "hianup05@gmail.com")
              Button(
                  onClick = {
                    buttonClickedState.value = !buttonClickedState.value
              }) {
                  Text(text = "Portfolio",
                  style = MaterialTheme.typography.button)
              }
              if (buttonClickedState.value){
                  Content()
              }else{
                  Box() {
                      
                  }
              }
              
          }
          }

  }
}

@Composable
fun CreateProfileInfo(name: String, profession: String, emailId: String ) {
    Column(modifier = Modifier.padding(5.dp)) {
        Text(text = name,
        style = MaterialTheme.typography.h4,
        color = MaterialTheme.colors.primary)
        Text(text = profession,
            Modifier.padding(3.dp),
            style=MaterialTheme.typography.subtitle1,)

        Text(text = emailId,
            Modifier.padding(3.dp),
            style = MaterialTheme.typography.subtitle1
        )

    }
}

@Composable
private fun CreateImageProfile(modifier: Modifier = Modifier) {
//Modifier = Modifier is something like optional as we can see the calling method CreateImageProfile() has not argument
    Surface(
        modifier = Modifier
            .size(100.dp)
            .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.LightGray),
        elevation = 5.dp,
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
    ) {
        Image(
            painter = painterResource(id = R.drawable.image),
            contentDescription = "ProfileImage",
            //modifier = modifier.size(100.dp),
            contentScale = ContentScale.Crop
        )
    }

}

@Preview
@Composable
fun Content(){
    Box(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
        .padding(5.dp)) {
        Surface(modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
            shape = RoundedCornerShape(size = 6.dp),
            border = BorderStroke(width = 2.dp,
                color = Color.LightGray)
        ) {
                PortFolio(data = listOf("Project 1", "Project 2","Project 3","Project 4","Project 5","Project 6","Project 7"))
        }

    }
}

@Composable
fun PortFolio(data: List<String>) {
    LazyColumn{
     items(data){
      item -> CreatePortfolio(item, "A great project indeed")
     }
    }


}

@Composable
fun CreatePortfolio(item: String, description: String) {
  Card(modifier = Modifier
      .padding(13.dp)
      .fillMaxWidth(),
      shape = RectangleShape,
      elevation = 7.dp) {

          Row(
          modifier = Modifier
              .padding(8.dp)
              .background(MaterialTheme.colors.surface)) {
            CreateImageProfile(modifier = Modifier.size(40.dp))
             Column(modifier = Modifier.padding(7.dp).align(alignment = CenterVertically)) {
                 Text(text = item,
                     style = MaterialTheme.typography.h6)
                 Text(text = description,
                     style = MaterialTheme.typography.body1)
             }
      }



  }
}

//@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetBizCardTheme {
        CreateBizCard()
    }
}