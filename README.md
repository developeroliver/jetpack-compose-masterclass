# Jetpack Compose Masterclass

Projet d'apprentissage Jetpack Compose en Kotlin pour Android.

---

## Layouts de base — Row & Column

### Concepts abordés

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

### Erreurs courantes à éviter

- Utiliser `fillMaxSize()` sur un enfant de `Column` ou `Row` : le premier enfant consomme tout l'espace disponible, les suivants deviennent invisibles et les arrangements (`SpaceAround`, etc.) n'ont plus d'effet. Préférer `fillMaxWidth()` dans une `Column`, et `fillMaxHeight()` dans une `Row`.
- Oublier d'appliquer le `modifier` reçu en paramètre (`modifier = modifier.fillMaxHeight()` et non `modifier = Modifier.fillMaxHeight()`).
- Couleur `@Preview` incomplète : `0xFFF` est invalide — utiliser le format ARGB complet `0xFFFFFFFF`.

### Exemple — `RowColumnDemo`

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
