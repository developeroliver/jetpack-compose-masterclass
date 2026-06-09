package com.example.jetpackcomposemasterclass.basic_layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposemasterclass.ui.theme.JetpackcomposemasterclassTheme

@Composable
fun RowColumnDemo(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.SpaceAround,
        modifier = modifier.fillMaxHeight()
    ) {
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text(
//                text = "Hello world!",
//                fontSize = 40.sp,
//                modifier = Modifier
//                    .alignByBaseline()
//            )
//            Text(
//                text = "Hello world!",
//                fontSize = 20.sp,
//                modifier = Modifier
//                    .alignByBaseline()
//            )

//            Box(
//                modifier = Modifier
//                    .size(100.dp)
//                    .background(Color.Red)
//            )
//            Box(
//                modifier = Modifier
//                    .size(100.dp)
//                    .background(Color.Blue)
//            )
//            Box(
//                modifier = Modifier
//                    .size(100.dp)
//                    .background(Color.Green)
//            )
//        }

        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Magenta)
                    .weight(1f)
            )
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Yellow)
                    .align(Alignment.Top)
                    .weight(1f)
            )
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Cyan)
                    .weight(2f)
            )
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
private fun RowColumnDemoPreview() {
    JetpackcomposemasterclassTheme {
        RowColumnDemo()
    }
}