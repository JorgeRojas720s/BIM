from sqlalchemy import Column, Date, Float
from sqlalchemy import ForeignKey
from sqlalchemy import Integer
from sqlalchemy import String
from sqlalchemy.orm import declarative_base
from sqlalchemy.orm import synonym


from sqlalchemy.ext.declarative import declarative_base

from data_access import *

session = Session()
Base = declarative_base()


class Object(Base):
    __tablename__ = "tbl_objects"

    obj_id  = Column(Integer, primary_key=True)
    obj_posX = Column(Float)
    obj_posY = Column(Float)
    obj_objectType = Column(String)
    obj_rotation = Column(Float)
    obj_flip = Column(Float)
    obj_width = Column(Float)
    obj_height = Column(Float)
    obj_fk_constructionPapers = Column(Integer)


    def create_new_object(self, posX, posY, objectType, rotation, flip, width, height, constructionPapers):
        print("LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL:" + rotation)
        existing_object = session.query(Object).get(id)
        
        if existing_object:
            print(f"El objeto ya existe")
            return "The object already exists"

        new_object = Object(obj_posX=posX,obj_posY=posY,obj_objectType=objectType,obj_rotation=rotation,obj_flip=flip,obj_width=width,
                            obj_height=height,obj_fk_constructionPapers=constructionPapers)

        session.add(new_object)
        session.commit()
        return "Object created!"
    
    def get_all_objects(self, constructionPapersId):
        try:
            objects_list = session.query(Object).filter_by(obj_fk_constructionPapers=constructionPapersId).all()
            return objects_list
        except Exception as e:
            print(f"Error al obtner objetos con id: {e}")
            return "Error al obtner objetos"
        
    def format_objects(objectList):
        formatted_data = []
        for object in objectList:
            object_data = f"-{object.obj_posX}|{object.obj_posY}|{object.obj_objectType}|{object.obj_rotation}|{object.obj_flip}|{object.obj_width}|{object.obj_height}\n"
            formatted_data.append(object_data)
        formatted_objects = ";".join(formatted_data)
        return formatted_objects
    
    def update_object(self, posX, posY, objectType, rotation, flip, width, height, constructionPapers):
        existing_object = session.query(Object).filter_by(obj_fk_constructionPapers=constructionPapers).first()

        if not existing_object:
            print(f"El objeto  no existe.")
            return "The object does not exist"

        existing_object.obj_posX = posX
        existing_object.obj_posY = posY
        existing_object.obj_objectType = objectType
        existing_object.obj_rotation = rotation
        existing_object.obj_flip = flip
        existing_object.obj_width = width
        existing_object.obj_height = height

        session.commit()
        return "Objects updated!"


#######################################################

def functions_object(javaData, formato):
    object = Object()
    response = "nada"

    if(javaData[1] == "newObject"):
        response = formato + str(object.create_new_object(javaData[2],javaData[3],javaData[4],javaData[5],javaData[6],javaData[7],javaData[8],javaData[9]))
    
    if(javaData[1] == "getObjects"):
        response =  formato + str(Object.format_objects(object.get_all_objects(javaData[2])))

    if(javaData[1] == "updateObjects"):
        response = formato + str(object.update_object(javaData[2], javaData[3], javaData[4], javaData[5], javaData[6], javaData[7], javaData[8], javaData[9]))

    return response