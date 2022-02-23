//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.commun.modele;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TableCorrespondance {
    private static Map<String, String> mapCorrespondanceNomsIndicateurs = new ConcurrentHashMap();

    public TableCorrespondance() {
    }

    private static void initialiserMap() {
        mapCorrespondanceNomsIndicateurs.put("PEDALO_I_Frequency_CM", "Fréquence de compilation");
        mapCorrespondanceNomsIndicateurs.put("PEDALO_I_Frequency_Execution", "Fréquence d'éxécution");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-AverageTime", "Temps moyen par question");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-CompilationFrequency", "Fréquence de compilation");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ExecutionFrequency", "Fréquence d'éxécution");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-BadFieldNameCaseBeginning", "Casse de la variable d'instance incorrecte");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-BadStaticFieldNameCase", "Casse de la variable statique incorrecte");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-BadMethodNameCase", "Casse de la méthode incorrecte");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-CompilationRate", "Taux de compilation");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ErrorCorrectionRate", "Taux de correction d'erreurs");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-JavaDoc", "Pourcentage de commentaires JavaDoc");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-NewXInClassX", "Présence d'un \"New X\" dans une classe nommée \"X\"");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-NoObjetReceveur", "Pas d'appel à l'objet receveur");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-PercentOfPrivateInstanceVariable", "Pourcentage de variables d'instance privées");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-PercentOfSignificantMethodName", "Pourcentage de noms de méthodes signifiatifs");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-PercentOfSignificantVariableName", "Pourcentage de noms de variables significatifs");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-NumberCompilationQuestion", "Nombre de compilation par question");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-GroupAverageProgress", "Avancement moyen du groupe");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-RelativeProgress", "Avancement par rapport au groupe");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ErrorStatus", "Existence d'erreurs de compilation");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassPointExist", "Absence de la classe \"Point\"");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassPointPublic", "La classe Point n'est pas publique");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassPoint2Fields", "La classe Point n'a pas exactement 2 champs");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassPointHasFieldsReal", "Le champ n'est pas de type réel dans la classe Point");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassPointHasFieldsPrivate", "Le champ n'est pas privé dans la classe Point");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassPointHasConstructor2Arguments", "Absence du constructeur spécifique Point à 2 arguments réels");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassPointHasConstructor2ArgumentsReal", "Les 2 arguments du constructeur ne sont pas tous réels");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassPointHasFielWith1Setter", "Absence de setter dans la classe Point");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassPointHasFielWith1Getter", "Absence de getter dans la classe Point");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassPointMethodEqualsExist", "Absence de la méthode \"equals\"");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassPointMethodEqualsPublic", "La méthode equals n'est pas publique");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassPointMethodEqualsTypeReturnBoolean", "Le type de retour de la méthode equals n'est pas booléen");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassPointMethodEquals1Argument", "La méthode equals ne doit avoir qu'un argument");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassPointMethodEqualsArgumentObject", "L'argument de la méthode equals n'est pas de type \"Object\"");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassPointMethodEqualsUsesInstanceOf", "La méthode equals n'utilise pas l'operateur instanceof");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassPointMethodDistanceExist", "Absence de la méthode \"distance\"");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassPointMethodDistancePublic", "La méthode distance n'est pas publique");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassPointMethodDistanceTypeReturnReal", "Le type de retour de la méthode distance n'est pas réel");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassPointMethodDistance1Argument", "La méthode distance n'a pas exactement 1 argument");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassPointMethodDistanceArgumentPoint", "L'argument de la méthode distance n'est pas de type Point");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassTriangleExist", "Absence de la classe \"Triangle\"");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassTrianglePublic", "La classe Triangle n'est pas publique");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassTriangle3Fields", "La classe Triangle n'a pas exactement 3 champs");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassTriangleHasFieldsPoint", "Le champ n'est pas de type Point dans la classe Triangle");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassTriangleHasFieldsPrivate", "Le champ n'est pas privé dans la classe Triangle");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassTriangleHasConstructor3Arguments", "Absence du constructeur spécifique à 3 arguments");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassTriangleHasConstructor3ArgumentsPoint", "Les 3 arguments du constructeur ne sont pas tous de type Point");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassTriangleHasFielWith1Getter", "Absence de getter dans la classe Triangle");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassTriangleHasFielWith1Setter", "Absence de setter dans la classe Triangle");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassTriangleNoExtendsPoint", "La classe Triangle hérite de Point");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassTriangleMethodPetimetreExist", "Absence de la méthode \"perimetre\"");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassTriangleMethodPetimetrePublic", "La méthode perimetre n'est pas publique");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassTriangleMethodPetimetreTypeReturnReal", "Le type de retour de la méthode perimetre n'est pas réel");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassTriangleMethodPetimetreZeroArgument", "La méthode perimetre ne doit pas avoir d'arguments");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassTriangleMethodPetimetreUseDistance", "La méthode perimetre n'utilise pas la méthode distance");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassTriangleMethodSurfaceExist", "Absence de la méthode \"surface\"");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassTriangleMethodSurfacePublic", "La méthode surface n'est pas publique");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassTriangleMethodSurfaceTypeReturnReal", "Le type de retour de la méthode surface n'est pas réel");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassTriangleMethodSurfaceZeroArgument", "La méthode surface ne doit pas avoir d'arguments");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassTriangleMethodSurfaceUseDistance", "La méthode surface n'utilise pas la méthode distance");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDroiteExist", "Absence de la classe \"Droite\"");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDroitePublic", "La classe Droite n'est pas publique");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDroite2Fields", "La classe Droite n'a pas 2 champs");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDroiteHasFieldsPrivate", "Le champ n'est pas privé dans la classe Droite");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDroiteHasFieldsReal", "Le champ n'est pas de type réel dans la classe Droite");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDroiteHasConstructor2Arguments", "Absence du constructeur spécifique Droite à 2 arguments");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDroiteHasConstructor2ArgumentsReal", "Les 2 arguments du constructeur Droite ne sont pas tous réels");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDroiteHasFielWith1Getter", "Absence de getter dans la classe Droite");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDroiteHasFielWith1Setter", "Absence de setter dans la classe Droite");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDroiteConstructor2ArgumentPointExist", "Absence du constructeur spécifique à 2 arguments de type Point");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassPointMethodEstSurDroiteExist", "Absence de la méthode \"estSurDroite\"");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassPointMethodEstSurDroitePublic", "La méthode estSurDroite n'est pas publique");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassPointMethodEstSurDroiteTypeReturnBoolean", "Le type de retour de la méthode estSurDroite n'est pas booléen");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassPointMethodEstSurDroite1Argument", "La méthode estSurDroite n'a pas exactement 1 argument");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassPointMethodEstSurDroiteArgumentDroite", "L'argument de la méthode estSurDroite n'est pas de type Droite");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDroiteMethodEstParalleleExist", "Absence de la méthode \"estParallele\"");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDroiteMethodEstParallelePublic", "La méthode estParallele n'est pas publique");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDroiteMethodEstParalleleTypeReturnBoolean", "Le type de retour de la méthode estParallele n'est pas booléen");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDroiteMethodEstParallele1Argument", "La méthode estParallele n'a pas exactement 1 argument");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDroiteMethodEstParalleleArgumentDroite", "L'argument de la méthode estParallele n'est pas de type Droite");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDroiteMethodIntersectionExist", "Absence de la méthode \"intersection\"");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDroiteMethodIntersectionPublic", "La méthode intersection n'est pas publique");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDroiteMethodIntersectionTypeReturnPoint", "Le type de retour de la méthode intersection n'est pas un Point");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDroiteMethodIntersection1Argument", "La méthode intersection n'a pas exactement 1 argument");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDroiteMethodIntersectionArgumentDroite", "L'argument de la méthode intersection n'est pas de type Droite");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDroiteMethodIntersectionUseParallele", "La méthode intersection n'utilise pas la méthode estParallele");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassTriangleConstructor3ArgumentDroiteExist", "Absence du constructeur Droite à 3 arguments de type Triangle");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassTriangleConstructor3ArgumentDroiteUseEstParallele", "Le constructeur Droite à 3 arguments n'utilise pas la méthode estParallele");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDateExist", "Absence de la classe \"Date\"");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDatePublic", "La classe Date n'est pas publique");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDate3Fields", "La classe Date n'a pas exactement 3 champs");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDateHasFieldsInt", "Le champ n'est pas de type int dans la classe Date");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDateHasFieldsPrivate", "Le champ n'est pas privé dans la classe Date");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDateHasConstructor3Arguments", "Absence du constructeur Date à 3 arguments");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDateHasConstructor3ArgumentsInt", "Les arguments du constructeur Date à 3 arguments ne sont pas entiers");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDateHasFieldWith1Setter", "Absence de setter dans la classe Date");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDateHasFieldWith1Getter", "Absence de getter dans la classe Date");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDateImplementsComparable", "La classe \"Date\" n'implémente pas Comparable");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDateMethodCompareToExist", "Absence de la méthode compareTo");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDateMethodCompareToPublic", "La méthode compareTo n'est pas publique");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDateMethodCompareToTypeReturnInt", "Le type de retour de la méthode compareTo n'est pas un entier");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDateMethodCompareTo1Argument", "La méthode compareTo ne doit avoir qu'un argument");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDateMethodCompareToArgumentObject", "L'argument de la méthode compareTo n'est pas de type \"Object\"");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDateMethodCompareToUsesInstanceOf", "La méthode compareTo n'utilise pas l'operateur instanceof");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDateMethodEqualsExist", "Pas de consistance entre compareTo et equals");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDateMethodEqualsPublic", "La méthode equals n'est pas publique dans la classe Date");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDateMethodEqualsTypeReturnBoolean", "Le type de retour de la méthode equals n'est pas booleen  dans la classe Date");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDateMethodEquals1Argument", "La méthode equals ne doit avoir qu'un argument dans la classe Date");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDateMethodEqualsArgumentObject", "L'argument de la méthode equals n'est pas de type \"Object\" dans la classe Date");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDateMethodEqualsUsesInstanceOf", "La méthode equals n'utilise pas l'operateur instanceof dans la classe Date");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassInvalidDateExceptionExist", "Absence de la classe \"InvalidDateException\"");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassInvalidDateExceptionExtendsException", "InvalidDateException n'hérite pas d'Exception");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassInvalidDateExceptionHas2Constructors", "La classe InvalidDateException n'a pas deux constructeurs");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassInvalidDateExceptionConstructorUsesSuper", "Le constructeur dans InvalidDateException n'utilise pas \"super\"");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDateConstructorThrowsInvalidDateException", "Le constructeur ne renvoie pas une InvalidDateException");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDateConstructorUsesThrow", "Le constructeur ne lève une InvalidDateException");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDateConstructorNotUseTry", "Le constructeur capture une InvalidDateException");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassTestExist", "Absence de la classe \"Test\"");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassTestPublic", "La classe Test n'est pas publique");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassTest0Field", "La classe Test a des champs");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassTestMethodMainExist", "Absence de la méthode main");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassTestMethodMainSignature", "Signature de la méthode main incorrecte");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassTestMethodMainUsesTryCatch", "La méthode main ne capture pas une InvalidDateException dans la classe Test");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassAuteurExist", "Absence de la classe \"Auteur\"");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassAuteurPublic", "La classe Auteur n'est pas publique");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassAuteur3Fields", "La classe Auteur n'a pas exactement 3 champs");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassAuteurHasFieldsPrivate", "Le champ n'est pas privé  dans la classe Auteur");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassAuteurHasFieldsStringDateDate", "Les champs ne sont pas de type String-Date-Date dans la classe Auteur");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassAuteurHasConstructor2Arguments", "Absence du constructeur Auteur à 2 arguments");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassAuteurHasConstructor2ArgumentsStringDate", "Les arguments du constructeur Auteur à 2 arguments ne sont de type String-Date");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassAuteurHasConstructor3Arguments", "Absence du constructeur Auteur à 3 arguments");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassAuteurHasConstructor3ArgumentsStringDateDate", "Les arguments du constructeur Auteur à 3 arguments ne sont pas de type String-Date-Date");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassAuteurMethodEqualsExist", "Absence de la méthode equals dans la classe Auteur");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassAuteurMethodEqualsPublic", "La méthode equals n'est pas publique dans la classe Auteur");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassAuteurMethodEqualsTypeReturnBoolean", "Le type de retour de la méthode equals n'est pas booléen dans la classe Auteur");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassAuteurMethodEquals1Argument", "La méthode equals ne doit avoir qu'un argument dans la classe Auteur");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassAuteurMethodEqualsArgumentObject", "L'argument de la méthode equals n'est pas de type \"Object\" dans la classe Auteur");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassAuteurMethodEqualsUsesInstanceOf", "La méthode equals n'utilise pas l'operateur instanceof dans la classe Auteur");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDocumentExist", "Absence de la classe \"Document\"");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDocumentPublic", "La Document n'est pas publique");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDocument2Fields", "La classe Document n'a pas exactement 2 champs");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDocumentHasFieldsPrivate", "Le champ n'est pas privé dans la classe Document");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDocumentHasFieldsAuteurString", "Les champs de la classe Document ne sont pas de type String-Auteur");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDocumentHasConstructor2Arguments", "Absence du constructeur Document à 2 arguments");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDocumentHasConstructor2ArgumentsAuteurString", "Les arguments du constructeur Document à 2 arguments ne sont pas de type String-Auteur");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDocumentMethodEqualsExist", "Absence de la méthode equals dans la classe Document");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDocumentMethodEqualsPublic", "La méthode equals n'est pas publique dans la classe Document");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDocumentMethodEqualsTypeReturnBoolean", "Le type de retour de la méthode equals n'est pas booléen dans la classe Document");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDocumentMethodEquals1Argument", "La méthode equals ne doit avoir qu'un argument dans la classe Document");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDocumentMethodEqualsArgumentObject", "L'argument de la méthode equals n'est pas de type \"Object\" dans la classe Document");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDocumentMethodEqualsUsesInstanceOf", "La méthode equals n'utilise pas l'operateur instanceof dans la classe Document");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDocumentMethodToStringExist", "Absence de la méthode toString");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDocumentMethodToStringSignature", "Signature de la méthode toString incorrecte");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDocumentHasFieldBoolean", "Absence du champ de type booléen modifiant le statut");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassDocumentMethodSetStatusSignature", "Problème dans la méthode modifiant le statut d'un Document");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassBaseDocumentsExist", "Absence de la classe \"BaseDocuments\"");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassBaseDocumentsPublic", "La BaseDocuments n'est pas publique");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassBaseDocuments1Field", "La classe BaseDocuments n'a pas exactement 1 champ");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassBaseDocumentsHasFieldsPrivate", "Le champ n'est pas privé dans la classe BaseDocuments");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassBaseDocumentsFieldList", "Absence du champ de type List ");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassBaseDocumentsMethodAjouteExist", "Absence de la méthode ajoute");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassBaseDocumentsMethodAjouteSignature", "Signature de la méthode ajoute incorrecte");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassBaseDocumentsMethodSupprimeExist", "Absence de la méthode supprime");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassBaseDocumentsMethodSupprimeSignature", "Signature de la méthode supprime incorrecte");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassBaseDocumentsMethodAfficheExist", "Absence de la méthode affiche");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassBaseDocumentsMethodAfficheSignature", "Signature de la méthode affiche incorrecte");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassBaseDocumentsMethodRendExist", "Absence de la méthode rend");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassBaseDocumentsMethodRendSignature", "Signature de la méthode rend incorrecte");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassBaseDocumentsMethodEstDisponibleExist", "Absence de la méthode estDisponible");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassBaseDocumentsMethodEstDisponibleSignature", "Signature de la méthode estDisponible incorrecte");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassBaseDocumentsMethodEstDisponibleThrowsNoSuchElementException", "La méthode estDisponible ne renvoie pas une NoSuchElementException");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassBaseDocumentsMethodEstDisponibleUsesThrow", "La méthode estDisponible ne lève une NoSuchElementException");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassBaseDocumentsMethodEstDisponibleNotUseTryCatch", "La méthode estDisponible capture une NoSuchElementException");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassBaseDocumentsMethodEmprunteExist", "Absence de la méthode emprunte");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassBaseDocumentsMethodEmprunteSignature", "Signature de la méthode emprunte incorrecte");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassBaseDocumentsMethodEmprunteThrowsNoSuchElementException", "La méthode emprunte ne renvoie pas une NoSuchElementException");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassBaseDocumentsMethodEmprunteUsesThrow", "La méthode emprunte ne lève une NoSuchElementException");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassBaseDocumentsMethodEmprunteNotUseTryCatch", "La méthode emprunte capture une NoSuchElementException");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassTestBaseExist", "Absence de la classe \"TestBase\"");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassTestBasePublic", "La classe TestBase n'est pas publique");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassTestBase0Field", "La classe TestBase a des champs");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassTestBaseMethodMainExist", "Absence de la méthode main");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassTestBaseMethodMainSignature", "Signature de la méthode main incorrecte dans la classe TestBase");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassTestBaseMethodMainUsesTryCatch", "La méthode main ne capture pas une NoSuchElementException dans la classe TestBase");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-InterfaceSelectionneurExist", "Absence de l'interface \"Selectionneur\"");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-InterfaceSelectionneurPublic", "L'interface Selectionneur n'est pas publique");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-InterfaceSelectionneurIsAnInterface", "Selectionneur n'est pas une interface");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-InterfaceSelectionneurMethodEstSatisfaitParExist", "Absence de la méthode estSatisfaitPar dans l'interface Selectionneur");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-InterfaceSelectionneurMethodEstSatisfaitParSignature", "Signature de la méthode estSatisfaitPar incorrecte dans l'interface Selectionneur");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassBaseDocumentsMethodSelectionneExist", "Absence de la méthode selectionne");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassBaseDocumentsMethodSelectionneSignature", "Signature de la méthode selectionne incorrecte");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassNonEmprunteSelectionneurExist", "Absence de la classe \"NonEmprunteSelectionneur\"");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassNonEmprunteSelectionneurPublic", "La classe NonEmprunteSelectionneur n'est pas publique");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassNonEmprunteSelectionneurImplementsSelectionneur", "La classe NonEmprunteSelectionneur n'implémente pas Selectionneur");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassNonEmprunteSelectionneurMethodEstSatisfaitParExist", "Absence de la méthode estSatisfaitPar dans la classe NonEmprunteSelectionneur");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassNonEmprunteSelectionneurMethodEstSatisfaitParSignature", "Signature de la méthode estSatisfaitPar incorrecte dans la classe NonEmprunteSelectionneur");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassAuteurVivantSelectionneurExist", "Absence de la classe \"AuteurVivantSelectionneur\"");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassAuteurVivantSelectionneurPublic", "La classe ClassAuteurVivantSelectionneur n'est pas publique");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassAuteurVivantSelectionneur1Field", "La classe ClassAuteurVivantSelectionneur n'a pas exactement 1 champ");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassAuteurVivantSelectionneurHasFieldPrivate", "Le champ n'est pas privé dans la classe AuteurVivantSelectionneur");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassAuteurVivantSelectionneurHasFieldDate", "Absence du champ de type Date dans la classe AuteurVivantSelectionneur");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassAuteurVivantSelectionneurHasConstructor1Argument", "Absence du constructeur à 1 argument dans la classe AuteurVivantSelectionneur");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassAuteurVivantSelectionneurHasConstructor1ArgumentDate", "L'argument du constructeur à 1 argument n'est pas de type Date dans la classe AuteurVivantSelectionneur");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassAuteurVivantSelectionneurImplementsSelectionneur", "La classe AuteurVivantSelectionneurn'implémente pas Selectionneur");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassAuteurVivantSelectionneurMethodEstSatisfaitParExist", "Absence de la méthode estSatisfaitPar dans la classe AuteurVivantSelectionneur");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassAuteurVivantSelectionneurMethodEstSatisfaitParSignature", "Signature de la méthode estSatisfaitPar incorrecte dans la classe AuteurVivantSelectionneur");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassMotDansTitreSelectionneurExist", "Absence de la classe \"MotDansTitreSelectionneur\"");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassMotDansTitreSelectionneurPublic", "La classe MotDansTitreSelectionneur n'est pas publique");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassMotDansTitreSelectionneur1Field", "La classe MotDansTitreSelectionneur n'a pas exactement 1 champ");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassMotDansTitreSelectionneurHasFieldPrivate", "Le champ n'est pas privé dans la classe MotDansTitreSelectionneur");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassMotDansTitreSelectionneurHasFieldString", "Absence du champ de type String dans la classe MotDansTitreSelectionneur");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassMotDansTitreSelectionneurHasConstructor1Argument", "Absence du constructeur à 1 argument dans la classe MotDansTitreSelectionneur");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassMotDansTitreSelectionneurHasConstructor1ArgumentString", "L'argument du constructeur à 1 argument n'est pas de type String dans la classe MotDansTitreSelectionneur");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassMotDansTitreSelectionneurImplementsSelectionneur", "La classe MotDansTitreSelectionneurn'implémente pas Selectionneur");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassMotDansTitreSelectionneurMethodEstSatisfaitParExist", "Absence de la méthode estSatisfaitPar dans la classe MotDansTitreSelectionneur");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassMotDansTitreSelectionneurMethodEstSatisfaitParSignature", "Signature de la méthode estSatisfaitPar incorrecte dans la classe MotDansTitreSelectionneur");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassSelectionneurCompositeExist", "Absence de la classe \"SelectionneurComposite\"");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassSelectionneurCompositePublic", "La classe SelectionneurComposite n'est pas publique");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassSelectionneurComposite1Field", "La classe SelectionneurComposite n'a pas exactement 1 champ");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassSelectionneurCompositeHasFieldPrivate", "Le champ n'est pas privé dans la classe SelectionneurComposite");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassSelectionneurCompositeHasFieldList", "Absence du champ de type List dans la classe SelectionneurComposite");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassSelectionneurCompositeImplementsSelectionneur", "La classe SelectionneurComposite n'implémente pas Selectionneur");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassSelectionneurCompositeMethodEstSatisfaitParExist", "Absence de la méthode estSatisfaitPar dans la classe SelectionneurComposite");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassSelectionneurCompositeMethodEstSatisfaitParSignature", "Signature de la méthode estSatisfaitPar incorrecte dans la classe SelectionneurComposite");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassSelectionneurCompositeMethodAddExist", "Absence de la méthode add");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassSelectionneurCompositeMethodAddSignature", "Signature de la méthode add incorrecte");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassTitreComparatorExist", "Absence de la classe \"TitreComparator\"");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassTitreComparatorPublic", "La classe TitreComparator n'est pas publique");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassTitreComparatorImplementsComparator", "La classe \"TitreComparator\" n'implémente pas Comparator");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassTitreComparatorMethodCompareExist", "Absence de la méthode compare");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassTitreComparatorMethodComparePublic", "La méthode compare n'est pas publique");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassTitreComparatorMethodCompareTypeReturnInt", "Le type de retour de la méthode compare n'est pas un entier");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassTitreComparatorMethodCompare2Arguments", "La méthode compare doit avoir 2 arguments");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassTitreComparatorMethodCompareArgumentsObject", "Les arguments de la méthode compare ne sont pas de type \"Object\"");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassTitreComparatorMethodCompareThrowsClassCastException", "La méthode compare ne renvoie pas une ClassCastException");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassBaseDocumentsMethodListeTrieeExist", "Absence de la méthode selectionne");
        mapCorrespondanceNomsIndicateurs.put("PEDALO-I-ClassBaseDocumentsMethodListeTrieeSignature", "Signature de la méthode selectionne incorrecte");
    }

    public static String changerNomIndicateur(String nom) {
        String intitule;
        if (mapCorrespondanceNomsIndicateurs.containsKey(nom)) {
            intitule = (String)mapCorrespondanceNomsIndicateurs.get(nom);
        } else {
            intitule = nom;
        }

        return intitule;
    }

    public void setMapCorrespondanceNomsIndicateurs(Map<String, String> mapCorrespondanceNomsIndicateurs) {
        TableCorrespondance.mapCorrespondanceNomsIndicateurs = mapCorrespondanceNomsIndicateurs;
    }

    public Map<String, String> getMapCorrespondanceNomsIndicateurs() {
        return mapCorrespondanceNomsIndicateurs;
    }

    static {
        initialiserMap();
    }
}
