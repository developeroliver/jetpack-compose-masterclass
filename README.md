# Jetpack Compose Masterclass

Projet d'apprentissage Jetpack Compose en Kotlin pour Android.

---

<details>
<summary><strong>Row & Column</strong></summary>

<br>

**`Column`** — dispose ses enfants verticalement.  
**`Row`** — dispose ses enfants horizontalement.  
**`Box`** — superpose ses enfants.

### Arrangements

| Paramètre | Rôle |
|---|---|
| `verticalArrangement` | Espacement vertical des enfants dans une `Column` |
| `horizontalArrangement` | Espacement horizontal des enfants dans une `Row` |
| `Arrangement.SpaceAround` | Espace égal autour de chaque enfant |
| `Arrangement.SpaceBetween` | Espace entre les enfants, sans marge aux extrémités |
| `Arrangement.SpaceEvenly` | Espace identique entre et autour de chaque enfant |

### Alignements

| Paramètre | Rôle |
|---|---|
| `verticalAlignment` | Alignement vertical des enfants dans une `Row` |
| `horizontalAlignment` | Alignement horizontal des enfants dans une `Column` |
| `Alignment.CenterVertically` | Centre chaque enfant sur l'axe vertical |
| `Alignment.Top / Bottom` | Aligne un enfant individuel en haut ou en bas dans une `Row` |
| `alignByBaseline()` | Aligne plusieurs `Text` sur leur ligne de base commune |

### Modificateurs de taille

| Modificateur | Effet |
|---|---|
| `fillMaxSize()` | Remplit la largeur **et** la hauteur disponibles |
| `fillMaxWidth()` | Remplit uniquement la largeur disponible |
| `fillMaxHeight()` | Remplit uniquement la hauteur disponible |
| `size(dp)` | Taille fixe en largeur et hauteur |
| `weight(n)` | Répartit l'espace restant proportionnellement entre les enfants d'une `Row` ou `Column` |

### Erreurs courantes

- Utiliser `fillMaxSize()` sur un enfant de `Column` ou `Row` : le premier enfant consomme tout l'espace, les suivants deviennent invisibles et les arrangements n'ont plus d'effet. Préférer `fillMaxWidth()` dans une `Column`, `fillMaxHeight()` dans une `Row`.
- Oublier d'appliquer le `modifier` reçu en paramètre (`modifier = modifier.fillMaxHeight()` et non `modifier = Modifier.fillMaxHeight()`).
- Couleur `@Preview` incomplète : `0xFFF` est invalide — utiliser le format ARGB complet `0xFFFFFFFF`.

### Exemple

```kotlin
@Composable
fun RowColumnDemo(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.SpaceAround,
        modifier = modifier.fillMaxHeight()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(modifier = Modifier.size(100.dp).background(Color.Red))
            Box(modifier = Modifier.size(100.dp).background(Color.Blue))
            Box(modifier = Modifier.size(100.dp).background(Color.Green))
        }
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(modifier = Modifier.size(100.dp).background(Color.Magenta).weight(1f))
            Box(modifier = Modifier.size(100.dp).background(Color.Yellow).align(Alignment.Top).weight(1f))
            Box(modifier = Modifier.size(100.dp).background(Color.Cyan).weight(2f))
        }
    }
}
```

</details>

---

<details>
<summary><strong>Box</strong></summary>

<br>

**`Box`** — superpose ses enfants les uns sur les autres. Utile pour créer des overlays, des dégradés, ou positionner un élément sur une image.

### Modificateurs spécifiques à Box

| Modificateur | Effet |
|---|---|
| `contentAlignment` | Alignement par défaut de tous les enfants dans le `Box` |
| `.align(Alignment.X)` | Aligne un enfant individuel (ex: `BottomEnd`, `TopStart`) |
| `.matchParentSize()` | L'enfant prend la même taille que le `Box` parent |

### Icons Material

Pour utiliser `Icons.Default.Star` (ou tout autre icône Material) :

1. Ajouter la dépendance dans `app/build.gradle.kts` (la BOM gère la version) :
```kotlin
implementation("androidx.compose.material:material-icons-extended")
```

2. Importer l'objet `Icons` **et** l'icône spécifique (extension property en Kotlin) :
```kotlin
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
```

> Sans l'import explicite de `filled.Star`, le compilateur ne peut pas résoudre `Icons.Default.Star`. `Icons.Default` est un alias de `Icons.Filled`.

### Dégradé avec Brush

```kotlin
Brush.verticalGradient(
    colors = listOf(Color.Transparent, Color.Black)
)
```

### Exemple

```kotlin
@Composable
fun BoxDemo(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            painter = painterResource(R.drawable.kermit),
            contentDescription = null
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black)
                    )
                )
        )
        IconButton(
            onClick = {},
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = Color.Yellow
            )
        }
    }
}
```

</details>

---

<details>
<summary><strong>FlowRow & FlowColumn</strong></summary>

<br>

**`FlowRow`** — comme `Row`, mais passe automatiquement à la ligne suivante quand les enfants dépassent la largeur disponible.  
**`FlowColumn`** — idem en vertical.

> API expérimentale : annoter avec `@OptIn(ExperimentalLayoutApi::class)`.

### Paramètres principaux

| Paramètre | Rôle |
|---|---|
| `horizontalArrangement` | Espacement horizontal des enfants sur chaque ligne |
| `verticalArrangement` | Espacement vertical entre les lignes |
| `maxItemsInEachRow` | Nombre maximum d'éléments par ligne |
| `maxLines` | Nombre maximum de lignes affichées |
| `overflow` | Comportement quand le contenu dépasse (`Clip`, `Visible`, `expandOrCollapseIndicator`) |

### Overflow — expand / collapse

`FlowRowOverflow.expandOrCollapseIndicator` permet d'afficher un bouton personnalisé pour étendre ou réduire le contenu qui dépasse `maxLines`.

```kotlin
overflow = FlowRowOverflow.expandOrCollapseIndicator(
    expandIndicator = {
        IconButton(onClick = {}) {
            Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "Expand")
        }
    },
    collapseIndicator = {
        IconButton(onClick = {}) {
            Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = "Collapse")
        }
    }
)
```

### Range Kotlin

```kotlin
for (i in 1..30) { ... }   // range inclusif de 1 à 30
```

### Exemple

```kotlin
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FlowLayoutDemo(modifier: Modifier = Modifier) {
    FlowRow(
        modifier = modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalArrangement = Arrangement.Center,
        maxItemsInEachRow = 3,
        maxLines = 4,
        overflow = FlowRowOverflow.expandOrCollapseIndicator(
            expandIndicator = {
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "Expand")
                }
            },
            collapseIndicator = {
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = "Collapse")
                }
            }
        )
    ) {
        for (i in 1..30) {
            AssistChip(onClick = {}, label = { Text("Item $i") })
        }
    }
}
```

</details>

---

<details>
<summary><strong>LazyColumn & LazyRow</strong></summary>

<br>

**`LazyColumn`** — liste verticale qui ne compose que les éléments visibles à l'écran (équivalent `RecyclerView`).  
**`LazyRow`** — idem en horizontal.

> Contrairement à `Column`/`Row`, les enfants doivent être déclarés via des fonctions DSL (`item`, `items`, `stickyHeader`…), pas directement comme composables.

### DSL LazyListScope

| Fonction | Rôle |
|---|---|
| `item { }` | Ajoute un seul élément |
| `items(n) { i -> }` | Ajoute `n` éléments indexés |
| `stickyHeader { }` | En-tête qui reste affiché en haut pendant le scroll |

### Paramètres principaux

| Paramètre | Rôle |
|---|---|
| `verticalArrangement` | Espacement vertical entre les items (`LazyColumn`) |
| `horizontalArrangement` | Espacement horizontal entre les items (`LazyRow`) |
| `contentPadding` | Padding intérieur autour du contenu (n'affecte pas le fond) |

### Erreurs courantes

- Placer un composable directement dans `LazyColumn` sans `item { }` : erreur de compilation car seules les fonctions DSL sont acceptées dans le bloc.
- Enchaîner `.background()` avec une virgule après le modifier précédent : la virgule termine l'argument, `.background()` n'est plus dans la chaîne.

### Exemple

```kotlin
@Composable
fun LazyListDemo(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(10) { i ->
                    Box(modifier = Modifier.size(100.dp).background(Color(Random.nextInt())))
                }
            }
        }
        items(100) { i -> Text("Item $i") }
        stickyHeader {
            Text(
                text = "STICKY HEADER",
                modifier = Modifier.fillMaxWidth().background(Color.Green)
            )
        }
        item {
            Text(
                text = "Reached the end of the list!",
                modifier = Modifier.fillMaxWidth().background(Color.Red)
            )
        }
    }
}
```

</details>

---

<details>
<summary><strong>LazyVerticalGrid & LazyHorizontalGrid</strong></summary>

<br>

**`LazyVerticalGrid`** — grille verticale paresseuse, ne compose que les cellules visibles.  
**`LazyHorizontalGrid`** — idem en horizontal.

### GridCells — définir les colonnes

| Valeur | Comportement |
|---|---|
| `GridCells.Fixed(n)` | Exactement `n` colonnes de largeur égale |
| `GridCells.FixedSize(dp)` | Colonnes d'une largeur fixe en dp, autant que possible |
| `GridCells.Adaptive(minDp)` | Colonnes d'au moins `minDp`, le nombre s'adapte à la largeur disponible |

> `Adaptive` est la valeur la plus responsive : elle calcule automatiquement le nombre de colonnes selon l'écran.

### Paramètres principaux

| Paramètre | Rôle |
|---|---|
| `columns` | Définit la structure des colonnes (`GridCells`) |
| `verticalArrangement` | Espacement vertical entre les lignes |
| `horizontalArrangement` | Espacement horizontal entre les colonnes |
| `contentPadding` | Padding intérieur autour du contenu |

### Exemple

```kotlin
@Composable
fun LazyGridDemo(modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(80.dp),
        modifier = Modifier.padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(50) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color(Random.nextInt()))
            )
        }
    }
}
```

</details>

---

<details>
<summary><strong>Scaffold & Snackbar animée</strong></summary>

<br>

**`Scaffold`** — structure de base Material 3 : `topBar`, `bottomBar`, `floatingActionButton`, `snackbarHost`.

### Snackbar personnalisée

Le `SnackbarHost` standard utilise une animation interne (fade/scale) non remplaçable. Pour une animation slide-from-bottom avec couleur personnalisée, on crée un composable séparé qui lit `hostState.currentSnackbarData` et le passe à `AnimatedVisibility`.

```kotlin
@Composable
fun AnimatedSnackbarHost(hostState: SnackbarHostState) {
    val currentSnackbarData = hostState.currentSnackbarData
    AnimatedVisibility(
        visible = currentSnackbarData != null,
        enter = slideInVertically(
            initialOffsetY = { it },
            animationSpec = tween(durationMillis = 300)
        ) + fadeIn(animationSpec = tween(300)),
        exit = slideOutVertically(
            targetOffsetY = { it },
            animationSpec = tween(durationMillis = 200)
        ) + fadeOut(animationSpec = tween(200))
    ) {
        currentSnackbarData?.let { data ->
            Snackbar(
                snackbarData = data,
                modifier = Modifier.padding(16.dp),
                containerColor = Color(0xFF6200EE),
                contentColor = Color.White
            )
        }
    }
}
```

Utilisation dans `Scaffold` :

```kotlin
snackbarHost = {
    AnimatedSnackbarHost(hostState = snackBarState)
}
```

### Afficher la Snackbar

```kotlin
val snackBarState = remember { SnackbarHostState() }
val scope = rememberCoroutineScope()

// Dans un onClick :
scope.launch {
    snackBarState.showSnackbar(message = "Clicked FAB")
}
```

### Points clés

- `slideInVertically { it }` — l'offset initial est égal à la hauteur totale du composable (entre par le bas)
- `+` combine deux animations (`slideIn + fadeIn`)
- `currentSnackbarData` est `null` quand aucune snackbar n'est active → `AnimatedVisibility` gère l'entrée/sortie automatiquement

</details>

---

<details>
<summary><strong>WindowSizeClass — Layouts adaptatifs</strong></summary>

<br>

**`WindowSizeClass`** — permet d'adapter le layout selon la taille de l'écran (téléphone, tablette, pliable).

### Dépendance

```kotlin
implementation("androidx.compose.material3.adaptive:adaptive")
```

### Breakpoints

| Constante | Largeur minimale |
|---|---|
| `WIDTH_DP_MEDIUM_LOWER_BOUND` | 600dp |
| `WIDTH_DP_EXPANDED_LOWER_BOUND` | 840dp |

### API actuelle

`WindowWidthSizeClass.COMPACT/MEDIUM/EXPANDED` est déprécié. Utiliser `isWidthAtLeastBreakpoint()` sur `WindowSizeClass` :

```kotlin
val windowClass = currentWindowAdaptiveInfo().windowSizeClass

when (windowClass.windowWidthSizeClass) {
    WindowWidthSizeClass.COMPACT,
    WindowWidthSizeClass.MEDIUM -> {
        MyLazyList()
    }
    WindowWidthSizeClass.EXPANDED -> {
        Row(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(3f)
                    .background(Color.Red)
            ) {
                Text("Menu option 1")
                Text("Menu option 2")
                Text("Menu option 3")
            }
            MyLazyList(
                modifier = Modifier
                    .weight(7f)
                    .fillMaxHeight()
            )
        }
    }
}
```

### Points clés

- `currentWindowAdaptiveInfo()` retourne toujours **compact** dans les previews — tester sur un émulateur tablette pour voir le layout expanded
- Passer `windowSizeClass` en paramètre au composable de contenu permet de le prévisualiser avec `WindowSizeClass.compute(width, height)`
- Le `modifier` des composables enfants doit utiliser le paramètre `modifier` (minuscule), pas `Modifier` (majuscule) pour que `weight()` et `fillMaxHeight()` passés par l'appelant soient bien appliqués

</details>
