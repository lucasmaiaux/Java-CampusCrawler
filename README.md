# 🎮 Campus Crawler

Un jeu d'aventure développé en Java et persistance des données via MySQL.

## 📋 Table des matières

- [Installation](#-installation)
  - [1. Cloner le repository](#1-cloner-le-repository)
  - [2. Configuration de la base de données](#2-configuration-de-la-base-de-données)
  - [3. Configuration du projet](#3-configuration-du-projet)
- [Compilation et exécution](#-compilation-et-exécution)
- [Structure du projet](#-structure-du-projet)
- [Dépendances](#-dépendances)
- [Dépannage](#-dépannage)

## 📦 Installation

### 1. Cloner le repository

```bash
# Cloner le projet
git clone https://github.com/lucasmaiaux/Java-CampusCrawler.git

# Accéder au dossier
cd Java-CampusCrawler
```

### 2. Configuration de la base de données

#### Étape 2.1 : Créer la base de données

Connectez-vous à MySQL et créez la base de données :

```sql
-- Se connecter à MySQL
mysql -u root -p

-- Créer la base de données
CREATE DATABASE javaquest;

-- Créer un utilisateur dédié (optionnel mais recommandé)
CREATE USER 'javaquest_user'@'localhost' IDENTIFIED BY 'votre_mot_de_passe';
GRANT ALL PRIVILEGES ON javaquest.* TO 'javaquest_user'@'localhost';
FLUSH PRIVILEGES;

-- Sélectionner la base de données
USE javaquest;
```

#### Étape 2.2 : Créer les tables

Exécutez le script SQL suivant pour créer les tables nécessaires :

```sql
-- Table structure for table `boards`
--

CREATE TABLE `boards` (
  `id` int(11) NOT NULL,
  `board` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL CHECK (json_valid(`board`))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `character_list`
--

CREATE TABLE `character_list` (
  `Id` int(11) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `Type` varchar(255) NOT NULL,
  `Health` int(11) NOT NULL,
  `MaxHealth` int(11) NOT NULL,
  `Attack` int(11) NOT NULL,
  `OffensiveEquipment` varchar(255) NOT NULL,
  `DefensiveEquipment` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `boards`
--
ALTER TABLE `boards`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `character_list`
--
ALTER TABLE `character_list`
  ADD PRIMARY KEY (`Id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `boards`
--
ALTER TABLE `boards`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `character_list`
--
ALTER TABLE `character_list`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;
```

## 📚 Dépendances

Le projet utilise les bibliothèques suivantes (incluses dans le dossier `lib/`) :

| Bibliothèque | Version | Utilisation |
|--------------|---------|-------------|
| **Gson** | 2.10.1 | Sérialisation/désérialisation JSON pour la sauvegarde |
| **MySQL Connector/J** | 9.3.0 | Connexion et interactions avec MySQL |
| **Protocol Buffers** | 4.29.0 | Dépendance du MySQL Connector |
| **JavaFX** | 11+ | Interface graphique (à installer séparément) |

## 🔧 Dépannage

### Problèmes courants et solutions

#### 1. Erreur : "JavaFX runtime components are missing"
**Solution :** Assurez-vous d'avoir ajouté les VM arguments JavaFX :
```bash
--module-path /path/to/javafx/lib --add-modules javafx.controls,javafx.fxml
```

#### 2. Erreur : "Access denied for user 'javaquest_user'@'localhost'"
**Solution :** Vérifiez les identifiants MySQL dans `SQLRepository.java` et assurez-vous que l'utilisateur existe et a les permissions nécessaires.

#### 3. Erreur : "Communications link failure"
**Solution :** 
- Vérifiez que MySQL est en cours d'exécution
- Vérifiez le port (par défaut 3306)
- Vérifiez le pare-feu

#### 4. Erreur : "ClassNotFoundException: com.mysql.cj.jdbc.Driver"
**Solution :** Assurez-vous que `mysql-connector-j-9.3.0.jar` est bien dans le classpath

#### 5. JavaFX non trouvé sur Java 11+
**Solution :** JavaFX n'est plus inclus dans le JDK depuis Java 11. Téléchargez-le séparément depuis [openjfx.io](https://openjfx.io/)

---

**Bon jeu !** 🎮
