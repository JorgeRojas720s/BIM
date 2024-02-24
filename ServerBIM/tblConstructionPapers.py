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


class ConstructionPaper(Base):
    __tablename__ = "tbl_construction_papers"

    con_id  = Column(Integer, primary_key=True)
    con_name = Column(String)


    def create_new_constructionPaper(self, name):
        new_ConstructionPaper = ConstructionPaper(con_name=name)

        session.add(new_ConstructionPaper)
        session.commit()
        return "Construction Paper created!"

    
    def get_construction_paper_id_by_name(self, name):
        try:
            construction_paper = session.query(ConstructionPaper).filter_by(con_name=name).first()

            if construction_paper:
                return construction_paper.con_id
            else:
                return f"No construction paper found"
        except Exception as e:
            return f"Error getting construction paper ID by name: {e}"
        

    def get_construction_paper_name_by_id(self, id):
        try:
            construction_paper_data = session.query(ConstructionPaper.con_name).filter(ConstructionPaper.con_id == id).one()
            construction_name, = construction_paper_data 

            return construction_name
        except Exception as e:
            return "Construction Paper was not found"
    


#######################################################

def functions_constructionPapers(javaData, formato):
    constructionPaper = ConstructionPaper()
    response = "nada"

    if(javaData[1] == "newConstructionPaper"):
        response = formato + str(constructionPaper.create_new_constructionPaper(javaData[2]))

    if(javaData[1] == "getConstructionPaper"):
        response = formato + str(constructionPaper.get_construction_paper_id_by_name(javaData[2]))
    
    if(javaData[1] == "getNameConstructionPaper"):
        response = formato + str(constructionPaper.get_construction_paper_name_by_id(javaData[2]))
    

    return response