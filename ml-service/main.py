from fastapi import FastAPI, File, UploadFile, HTTPException
from fastapi.middleware.cors import CORSMiddleware
from PIL import Image
import io
import numpy as np
import tensorflow as tf
import json
import os

app = FastAPI(title="Tissue Captioning ML Service", version="1.0.0")

# Configuration CORS - autoriser tout
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

# Modèle placeholder - à remplacer par votre vrai modèle
class TissueCaptioningModel:
    def __init__(self):
        # Ici vous chargerez votre vrai modèle Keras
        # self.model = tf.keras.models.load_model('path_to_your_model')
        pass
    
    def predict(self, image):
        # Placeholder - remplacez par votre vraie logique de prédiction
        # return self.model.predict(image)
        return "Tissue sample showing normal cellular structure with clear nuclear boundaries and well-defined cytoplasmic regions."

# Instance du modèle
model = TissueCaptioningModel()

@app.get("/")
async def root():
    return {"message": "Tissue Captioning ML Service is running"}

@app.get("/health")
async def health_check():
    return {"status": "healthy", "service": "ml-service"}

@app.post("/generate")
async def generate_caption(file: UploadFile = File(...)):
    """
    Génère une légende pour une image de tissu
    """
    try:
        # Vérifier le type de fichier
        if not file.content_type.startswith('image/'):
            raise HTTPException(status_code=400, detail="Le fichier doit être une image")
        
        # Lire l'image
        image_data = await file.read()
        image = Image.open(io.BytesIO(image_data))
        
        # Convertir en format compatible avec le modèle
        # image = image.resize((224, 224))  # Ajustez selon votre modèle
        # image_array = np.array(image) / 255.0  # Normalisation
        
        # Générer la légende
        caption = model.predict(image)
        
        return {
            "caption": caption,
            "filename": file.filename,
            "size": len(image_data),
            "format": image.format
        }
        
    except Exception as e:
        raise HTTPException(status_code=500, detail=f"Erreur lors de la génération: {str(e)}")

if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="0.0.0.0", port=8001)
