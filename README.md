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
