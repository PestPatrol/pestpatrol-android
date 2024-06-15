package com.feature.home_presentation.screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.core.common.resource.IconSearch
import com.core.common.ui.HintTextColor
import com.core.common.ui.PrimaryTextColor
import com.core.common.ui.SFProDisplayRegular
import com.feature.home_presentation.R

@Composable
fun HomeSearchField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholderText: String = stringResource(id = R.string.search_paddy_dots),
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(Color.White)
    ) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedLeadingIconColor = Color.Black,
                unfocusedLeadingIconColor = Color.Black,
                cursorColor = PrimaryTextColor
            ),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = IconSearch),
                    contentDescription = stringResource(
                        R.string.search
                    )
                )
            },
            placeholder = {
                Text(
                    text = placeholderText,
                    fontFamily = SFProDisplayRegular,
                    fontSize = 16.sp,
                    color = HintTextColor
                )
            },
            textStyle = TextStyle(
                fontFamily = SFProDisplayRegular,
                color = PrimaryTextColor,
                fontSize = 16.sp
            )
        )
    }
}