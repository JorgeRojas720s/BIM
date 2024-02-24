from sqlalchemy import Column, Date
from sqlalchemy import ForeignKey
from sqlalchemy import Integer
from sqlalchemy import String
from sqlalchemy.orm import declarative_base
from sqlalchemy.orm import synonym


from sqlalchemy.ext.declarative import declarative_base

from data_access import *

session = Session()
Base = declarative_base()


class Proyect(Base):
    __tablename__ = "tbl_proyects"

    pro_code  = Column(Integer, primary_key=True)
    pro_name = Column(String)
    pro_starDate = Column(Date)
    pro_endDate = Column(Date)
    pro_engineer = Column(Integer)
    pro_designer = Column(Integer)
    pro_fk_constructionPapers  = Column(Integer)

    def create_new_proyect(self, code, name, startDate, endDate, engineer, designer):
        try:
            existing_project = session.query(Proyect).get(code)

            if existing_project:
                print(f"El proyecto ya existe")
                return "The project already exists"

            new_project = Proyect(pro_code=code,pro_name=name,pro_starDate=startDate,pro_endDate=endDate,pro_engineer=engineer,pro_designer=designer)

            session.add(new_project)
            session.commit()
            return "Project created!"
        except Exception as e:
            session.rollback()
            return f"Invalid engineer or designer"
    
    def select_all(self):
        usersList = session.query(Proyect).all()
        return usersList
    
    def select_designer_proyect(self, designer_id):
        projects_list = session.query(Proyect).filter(Proyect.pro_designer == designer_id).all()
        return projects_list

    
    def format_proyects(proyectList):
        formatted_data = []
        for proyect in proyectList:
            proyect_data = f"-{proyect.pro_name}|{proyect.pro_code}|{proyect.pro_starDate}|{proyect.pro_endDate}|{proyect.pro_engineer}|{proyect.pro_designer}\n"
            formatted_data.append(proyect_data)
        formatted_proyects = ";".join(formatted_data)
        return formatted_proyects
    
    def delete_proyect_by_code(self, code):
        try:
            project = session.query(Proyect).filter(Proyect.pro_code == code).first()

            if project:
                session.delete(project)
                session.commit()
                return f"Project deleted"
            else:
                return f"Project does not exist"
        except Exception as e:
            print(f"Error al eliminar el proyrcto con code  {code}: {e}")
            session.rollback()

        
    ##ver luego si hay prblemas, toque aqui la foreign
    def select(self, code):
        proyectData = session.query(Proyect.pro_name, Proyect.pro_starDate, Proyect.pro_endDate, Proyect.pro_engineer, 
                                Proyect.pro_designer, Proyect.pro_fk_constructionPapers).filter(Proyect.pro_code == code).first()

        if proyectData:
            proyect_name = proyectData[0]     
            proyect_startDate = proyectData[1]     
            proyect_endDate = proyectData[2]     
            proyect_engineer = proyectData[3]     
            proyect_designer = proyectData[4]   
            proyect_ConstructionPaper = proyectData[5]   
            return f"{proyect_name}|{proyect_startDate}|{proyect_endDate}|{proyect_engineer}|{proyect_designer}|{proyect_ConstructionPaper}"
        else:
            return f"No se encontro"

    
    def update_proyects(self, code, name=None, starDate=None, endDate=None,engineer=None, designer=None):
        try:
            proyect = session.query(Proyect).filter(Proyect.pro_code == code).first()
            
            if proyect:
                if name is not None:
                    proyect.pro_name = name
                if starDate is not None:
                    proyect.pro_starDate = starDate
                if endDate is not None:
                    proyect.pro_endDate = endDate
                if engineer is not None:
                    proyect.pro_engineer = engineer
                if designer is not None:
                    proyect.pro_designer = designer

                session.commit()
                return f"Updated project"
            else:
                return f"Project not found"
        except Exception as e:
            print(f"Error al actualizar el Proyecto: {e}")
            session.rollback()
            return f"Invalid engineer or designer"
        
    def update_proyect_by_code(self, code, constructionPapers=None):
        try:
            proyect = session.query(Proyect).get(code)

            if proyect:
                if constructionPapers is not None:
                    proyect.pro_fk_constructionPapers = constructionPapers

                session.commit()
                return f"Updated project"
            else:
                return f"Project not found"
        except Exception as e:
            print(f"Error al actualizar el Proyecto: {e}")
            session.rollback()
            return f"Error al actualizar el Proyecto: {e}"
        


#######################################################

def functions_proyect(javaData, formato):
    proyect = Proyect()
    response = "nada"

    if(javaData[1] == "newProyect"):
        print("Creando new Proyectooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo")
        response = formato + str(proyect.create_new_proyect(javaData[2],javaData[3],javaData[4],javaData[5],javaData[6],javaData[7]))
    
    if(javaData[1] == "deleteProyect"):
        response = formato + str(proyect.delete_proyect_by_code(javaData[2]))
    
    if(javaData[1] == "getAllProyects"):
        response = formato + str(Proyect.format_proyects(proyect.select_all()))

    if(javaData[1] == "queryProyect"):
        response = formato + str(proyect.select(javaData[2]))
    
    if(javaData[1] == "updateProyect"):
        response = formato + str(proyect.update_proyects(javaData[2], javaData[3],javaData[4],javaData[5],javaData[6],javaData[7]))

    if(javaData[1] == "getDesignerProyects"):
        response = formato + str(Proyect.format_proyects(proyect.select_designer_proyect(javaData[2])))

    if(javaData[1] == "updateProyectByCode"):
        response = formato + str(proyect.update_proyect_by_code(javaData[2], javaData[3]))

    return response