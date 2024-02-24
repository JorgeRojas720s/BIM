# data_access.py

from sqlalchemy.orm import sessionmaker
from sqlalchemy import create_engine

# Definir el motor y la sesión
connector = "mariadb+mariadbconnector"
endpoint = "localhost"
user = "root"
passwd = ""
database = "db_bim"
SQLALCHEMY_DATABASE_URI = "{}://{}:{}@{}:3306/{}".format(connector, user, passwd, endpoint, database)
engine = create_engine(SQLALCHEMY_DATABASE_URI)
Session = sessionmaker(bind=engine)
