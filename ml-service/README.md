# ML Service - Tissue Captioning

Service FastAPI pour la génération automatique de légendes d'images de tissus.

## Installation

```bash
pip install -r requirements.txt
```

## Lancement

```bash
python main.py
```

Le service sera accessible sur `http://localhost:8001`

## Endpoints

- `GET /` - Page d'accueil
- `GET /health` - Vérification de santé
- `POST /generate` - Génération de légende (multipart image)

## Intégration du modèle

1. Placez vos 3 fichiers de modèle dans ce dossier
2. Modifiez la classe `TissueCaptioningModel` dans `main.py`
3. Chargez votre modèle Keras avec `tf.keras.models.load_model()`
4. Adaptez la logique de prédiction selon votre modèle

## Test

```bash
curl -X POST "http://localhost:8001/generate" \
     -H "accept: application/json" \
     -H "Content-Type: multipart/form-data" \
     -F "file=@your_image.jpg"
```
