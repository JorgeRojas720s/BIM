from sqlalchemy import Column
from sqlalchemy import ForeignKey
from sqlalchemy import Integer
from sqlalchemy import String
from sqlalchemy.orm import declarative_base
from sqlalchemy.orm import synonym


from sqlalchemy.ext.declarative import declarative_base

from data_access import *

session = Session()
Base = declarative_base()


class User(Base):
    __tablename__ = "tbl_users"
    #usr_id = Column(Integer, primary_key=True)
    #usr_name = Column(String(100))

    usr_id = Column(Integer, primary_key=True)
    usr_name = Column(String)
    usr_lastName = Column(String)
    usr_status = Column(String)
    usr_username = Column(String)
    usr_email = Column(String)
    usr_password = Column(String)
    usr_role = Column(String)



    def check_credentials(self, username_or_email, password):
        user = Session().query(User).filter(((User.usr_username == username_or_email) | (User.usr_email == username_or_email)) &
                                                (User.usr_password == password)).first()

        if user:
            return f"{user.usr_role}|{user.usr_status}|{user.usr_id}"
        else:
            return None
        
    def get_name_by_username(self, username_or_email):
        user = Session().query(User).filter(((User.usr_username == username_or_email) | (User.usr_email == username_or_email))).first()

        if user:
            return f"{user.usr_name}|{user.usr_lastName}"
        else:
            return None


    def create_new_user(self, id, name, lastName, status, username, email, password, role):
        try:
            existing_user = session.query(User).get(id)

            if existing_user:
                return "User already exists"

            new_user = User(usr_id=id,usr_name=name,usr_lastName=lastName,usr_status=status,usr_username=username,
                            usr_email=email,usr_password=password,usr_role=role)

            session.add(new_user)
            session.commit()

            return f"User created"
        except Exception as e:
            return "No se pudo"

    def select_all(self):
        usersList = session.query(User).all()
        return usersList

    def format_users(usersList):
        formatted_data = []
        for user in usersList:
            user_data = f"-{user.usr_id}|{user.usr_name}|{user.usr_lastName}|{user.usr_status}|{user.usr_role}\n"
            formatted_data.append(user_data)
        formatted_users = ";".join(formatted_data)
        return formatted_users

    def select(self, id):
        try:
            userData = session.query(User.usr_name, User.usr_lastName, User.usr_status, User.usr_username, 
                                    User.usr_email, User.usr_password, User.usr_role).filter(User.usr_id == id).one()
            
            user_name, user_lastName, user_status, user_username, user_email, user_password, user_role = userData

            return f"{user_name}|{user_lastName}|{user_status}|{user_username}|{user_email}|{user_password}|{user_role}"
        except Exception as e:
            return "User was not found"

    def delete_user_by_id(self, id):
        try:
            user = session.query(User).filter(User.usr_id == id).first()

            if user:
                session.delete(user)
                session.commit()
                return f"User deleted"
            else:
                return f"User does not exist"
        except Exception as e:
            print(f"Error al eliminar el usuarios {id} : {e}")
            session.rollback()
            

    def update_user(self, id, name=None, lastName=None, status=None, username=None, email=None, password=None, role=None):
        try:
            user = session.query(User).filter(User.usr_id == id).first()
            
            if user:
                if name is not None:
                    user.usr_name = name
                if lastName is not None:
                    user.usr_lastName = lastName
                if status is not None:
                    user.usr_status = status
                if username is not None:
                    user.usr_username = username
                if email is not None:
                    user.usr_email = email
                if password is not None:
                    user.usr_password = password
                if role is not None:
                    user.usr_role = role

                session.commit()
                return f"User update"
            else:
                return f"User not update"
        except Exception as e:
            print(f"Error al actualizar el usuario: {e}")
            session.rollback()
            return f"Error al actualizar el usuario: {e}"
        
    def get_engineers_and_designers(self):
        engineers_and_designers = session.query(User).filter(
            ((User.usr_role == 'Engineer') | (User.usr_role == 'Designer')) &
            (User.usr_status == 'Active')
        ).all()
        return engineers_and_designers

#es como el toSring creo
    def __repr__(self):
        return f"User(id={self.usr_id!r}, name={self.usr_name!r})"


#######################################################################
def functions_user(javaData, formato):
    ##Profe sugerio no hacer todos esos return, sino hacer una variable que guarde el contenido y al final solo haya un return con es0
    ##
    ##Pienso que es mas eficiente los return xd, para que devuelva la respuesta cuando la tiene
    user = User()
    response = "nada"

    if(javaData[1] == "consulta"):
        print("user: " + javaData[2])
        print("pass: " + javaData[3])
        response = formato + str(user.check_credentials(javaData[2],javaData[3]))

    if(javaData[1] == "newUser"):
        response = formato + str(user.create_new_user(javaData[2],javaData[3],javaData[4],
                                                    javaData[5],javaData[6],javaData[7],javaData[8],javaData[9]))
    if(javaData[1] == "getAllUsers"):
        response = formato + str(User.format_users(user.select_all()))

    if(javaData[1] == "queryUser"):
        response = formato + str(user.select(javaData[2]))

    if(javaData[1] == "deleteUser"):
        response = formato + str(user.delete_user_by_id(javaData[2]))

    if(javaData[1] == "updateUser"):
        response =  formato + str(user.update_user(javaData[2],javaData[3],javaData[4],
                                                    javaData[5],javaData[6],javaData[7],javaData[8],javaData[9]))
    if(javaData[1] == "getEngAndDesig"):
        response =  formato + str(User.format_users(user.get_engineers_and_designers()))

    if(javaData[1] == "getName"):
        response =  formato + str(user.get_name_by_username(javaData[2]))

    return response