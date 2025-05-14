package com.example.unitconverter
    import android . os . Bundle
            import androidx . activity . ComponentActivity
            import androidx . activity . compose . setContent
            import androidx . compose . foundation . layout . Arrangement
            import androidx . compose . foundation . layout . Box
            import androidx . compose . foundation . layout . Column
            import androidx . compose . foundation . layout . Row
            import androidx . compose . foundation . layout . Spacer
            import androidx . compose . foundation . layout . fillMaxSize
            import androidx . compose . foundation . layout . fillMaxWidth
            import androidx . compose . foundation . layout . height
            import androidx . compose . foundation . layout . width
            import androidx . compose . material . icons . Icons
            import androidx . compose . material . icons . filled . ArrowDropDown
            import androidx . compose . material3 . Button
            import androidx . compose . material3 . DropdownMenu
            import androidx . compose . material3 . DropdownMenuItem
            import androidx . compose . material3 . Icon
            import androidx . compose . material3 . MaterialTheme
            import androidx . compose . material3 . OutlinedTextField
            import androidx . compose . material3 . Surface
            import androidx . compose . material3 . Text
            import androidx . compose . runtime . Composable
            import androidx . compose . runtime . getValue
            import androidx . compose . runtime . mutableStateOf
            import androidx . compose . runtime . remember
            import androidx . compose . runtime . setValue
            import androidx . compose . ui . Alignment
            import androidx . compose . ui . Modifier
            import androidx . compose . ui . tooling . preview . Preview
            import androidx . compose . ui . unit . dp
            import com . example . unitconverter . ui . theme . UnitConverterTheme
            import kotlin . math . roundToInt

    class MainActivity : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContent {
                UnitConverterTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        UnitConverter()
                    }
                }
            }
        }
    }

    @Composable
    fun UnitConverter() {
        var inputValue by remember { mutableStateOf("") }
        var outputValue by remember { mutableStateOf("") }
        var inputUnit by remember { mutableStateOf("Rupees") }
        var outputUnit by remember { mutableStateOf("Rupees") }
        var iExpanded by remember { mutableStateOf(false) }
        var oExpanded by remember { mutableStateOf(false) }
        val conversionFactor = remember { mutableStateOf(1.00) }
        val oConversionFactor = remember { mutableStateOf(1.00) }



        fun convertUnits() {
            val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
            val result =
                (inputValueDouble / conversionFactor.value * oConversionFactor.value * 100).roundToInt() / 100.0
            outputValue = result.toString()
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//        This Fun prints text one by one
            Text(
                "Currency Converter",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )  // Modifier.padding(100.dp) if you want to add spacing as per your requirements in all directions
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = inputValue,
                onValueChange = {
                    inputValue = it
                    convertUnits()
                },
                label = { Text("Enter Value") },
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row {
//        This fun prints text next to each other
                Box {
                    Button(onClick = { iExpanded = true }) {
                        Text(inputUnit)
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "")

                    }
                    DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                        DropdownMenuItem(
                            text = { Text("Rupees") },
                            onClick = {
                                iExpanded = false
                                inputUnit = "Rupees"
                                conversionFactor.value = 1.00
                                convertUnits()
                            }
                        )
                        DropdownMenuItem(text = { Text("USD") },
                            onClick = {
                                iExpanded = false
                                inputUnit = "USD"
                                conversionFactor.value = 0.012
                                convertUnits()
                            }
                        )
                        DropdownMenuItem(text = { Text("Ruble") },
                            onClick = {
                                iExpanded = false
                                inputUnit = "Ruble"
                                conversionFactor.value = 0.94
                                convertUnits()
                            }
                        )
                        DropdownMenuItem(text = { Text("Yen") },
                            onClick = {
                                iExpanded = false
                                inputUnit = "Yen"
                                conversionFactor.value = 1.71
                                convertUnits()
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Box {
                    Button(onClick = { oExpanded = true }) {
                        Text(outputUnit)
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "")

                    }
                    DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                        DropdownMenuItem(text = { Text("Rupees") },
                            onClick = {
                                oExpanded = false
                                outputUnit = "Rupees"
                                oConversionFactor.value = 1.00
                                convertUnits()
                            }
                        )
                        DropdownMenuItem(text = { Text("USD") },
                            onClick = {
                                oExpanded = false
                                outputUnit = "USD"
                                oConversionFactor.value = 0.012
                                convertUnits()
                            }
                        )
                        DropdownMenuItem(text = { Text("Ruble") },
                            onClick = {
                                oExpanded = false
                                outputUnit = "Ruble"
                                oConversionFactor.value = 0.94
                                convertUnits()
                            }
                        )
                        DropdownMenuItem(text = { Text("Yen") },
                            onClick = {
                                oExpanded = false
                                outputUnit = "Yen"
                                oConversionFactor.value = 1.71
                                convertUnits()
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Result: $outputValue $outputUnit",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun UnitConverterPreview() {
        UnitConverter()
    }
