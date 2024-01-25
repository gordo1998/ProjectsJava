/*
Descripción: Creación y gestión base de datos de libros y autores para la aplicación de la biblioteca
Fecha: 17/05/2023
Author: Jordi Barreda Álvarez
*/
drop database bibliotecaJava;
Create database bibliotecaJava;
use bibliotecaJava;
# Esta tabla es la de paises que vincularemos con la tabla autores
create table paises
(
idPais int auto_increment,
nombrePais varchar(60) not null,
constraint pk_pais primary key (idPais)
)engine=InnoDB;

# Esta tabla es la de autores que será la que referenciaremos a la tabla libros
create table autores
(
nombreAutor varchar(40) not null,
apellidoAutor varchar(40) not null,
apellidoDosAutor varchar(40) not null,
pais int, 
constraint pk_autores primary key (nombreAutor, apellidoAutor, apellidoDosAutor),
constraint fk_pais foreign key (pais)
references paises (idPais)
on update cascade
on delete cascade
)engine=InnoDB;

# Esta tabla contiene todos los géneros de los libros
create table generos
(
idGenero int auto_increment,
tipo varchar(40) not null,
constraint pk_genero primary key (idGenero)
)engine=InnoDB;

# Esta tabla es la de libros y estará vinculada con la tabla autores.
create table libros
(
ISBN varchar(14) not null,
tituloLibro varchar(100) not null,
AutorNombre varchar(40) not null,
AutorApellido varchar(40) not null,
AutorApellidoDos varchar(40) not null,
numeroPaginas int,
Genero int,
constraint pk_libros primary key (ISBN),
constraint fk_autor foreign key (AutorNombre, AutorApellido, AutorApellidoDos)
references autores (nombreAutor, apellidoAutor, apellidoDosAutor) 
on update cascade
on delete cascade,
constraint fk_genero foreign key (Genero)
references generos (idGenero)
on update cascade
on delete cascade
)engine=InnoDB;

# Vamos a crear un procedure para hacer un insert en la tabla paises
delimiter //
create procedure pInsertPaises
(
IN pnombrePais varchar(60)
)
begin
	INSERT INTO paises (nombrePais)
    VALUES (pnombrePais);
end//
delimiter ;

# Este procedure contiene una transacción de inserción "masiva" en la tabla paises
delimiter //
create procedure pInsercionPaisesAll()
begin
	declare exit handler for sqlexception
    begin
		select 'No se ha podido realizar la transacción';
		rollback;
    end;
    
    start transaction;
	CALL pInsertPaises('España');
	CALL pInsertPaises('Francia');
	CALL pInsertPaises('Italia');
	CALL pInsertPaises('Alemania');
	CALL pInsertPaises('Reino Unido');
	CALL pInsertPaises('Estados Unidos');
	CALL pInsertPaises('Canadá');
	CALL pInsertPaises('México');
	CALL pInsertPaises('Brasil');
	CALL pInsertPaises('Argentina');
	CALL pInsertPaises('Colombia');
	CALL pInsertPaises('Chile');
	CALL pInsertPaises('Perú');
	CALL pInsertPaises('Australia');
	CALL pInsertPaises('Japón');
    commit;
end //
delimiter ;
select * from libros;
# Este procedure nos genera un insert de la tabla autores
delimiter //
create procedure pInsertAutor
(
IN pnombreAutor varchar(40),
IN papellidoAutor varchar(40),
IN papellidoAutorDos varchar(40),
IN pPais varchar(60)
)
begin
	declare vIdPais int;
    declare vAutores int;
    
    select count(*) into vAutores from autores
    where nombreAutor = pnombreAutor and
    apellidoAutor = papellidoAutor and
    apellidoDosAutor = papellidoAutorDos;
    
    set vIdPais = (select idPais from paises where nombrePais = pPais);
    
    if vAutores > 0 then
		signal sqlstate '45000'
        set message_text = 'Este autor ya existe!';
	else
		insert into autores (nombreAutor, apellidoAutor, apellidoDosAutor, pais)
		values (pnombreAutor, papellidoAutor, papellidoAutorDos, vIdPais);
    end if;
end //
delimiter ;
delimiter //
create procedure peliminarAutor
(
IN pNombre varchar(40),
IN pApellido varchar(40),
IN pApellidoDos varchar(40)
)
begin
	delete from autores 
    where nombreAutor = pNombre 
    and apellidoAutor = pApellido 
    and apellidoDosAutor = pApellidoDos;
end //
delimiter ;

delimiter //
create procedure pActualizarNombreAutor
(
IN pnombreNuevo varchar(40),
IN pnombreAutor varchar(40),
IN papellidoAutor varchar(40),
IN papellidoDosAutor varchar(40)
)
begin
	update autores set nombreAutor = pnombreNuevo 
    where nombreAutor = pnombreAutor and
    apellidoAutor = papellidoAutor and
    apellidoDosAutor = papellidoDosAutor;
end //
delimiter ;

delimiter //
create procedure pActualizarApellidoAutor
(
IN papellidoNuevo varchar(40),
IN pnombreAutor varchar(40),
IN papellidoAutor varchar(40),
IN papellidoDosAutor varchar(40)
)
begin
	update autores set apellidoAutor = papellidoNuevo
    where nombreAutor = pnombreAutor and
    apellidoAutor = papellidoAutor and
    apellidoDosAutor = papellidoDosAutor;
end //
delimiter ;
delimiter //
create procedure pActualizarApellidoDosAutor
(
IN papellidoDosNuevo varchar(40),
IN pnombreAutor varchar(40),
IN papellidoAutor varchar(40),
IN papellidoDosAutor varchar(40)
)
begin 
	update autores set apellidoDosAutor = papellidoDosNuevo
    where nombreAutor = pnombreAutor and
    apellidoAutor = papellidoAutor and
    apellidoDosAutor = papellidoDosAutor;
end //
delimiter ;


delimiter //
create procedure pActualizarPaisAutor
(
IN pPaisNuevo varchar(60),
IN pnombreAutor varchar(40),
IN papellidoAutor varchar(40),
IN papellidoDosAutor varchar(40)
)
begin
	declare vPais int;
	set vPais = (select idPais from paises where nombrePais = pPaisNuevo);
	update autores set pais = vPais
    where nombreAutor = pnombreAutor and
    apellidoAutor = papellidoAutor and
    apellidoDosAutor = papellidoDosAutor;
end //
delimiter ;

delimiter //
create procedure pInsertarGenero
(
IN pTipo varchar(40)
)
begin
	insert into generos (tipo) values(pTipo);
end //
delimiter ;

# transaccion de todos los generos
delimiter //
create procedure pInsertarTodosGeneros()
begin 
	declare exit handler for sqlexception
    begin
		select 'No se ha completado la transacción';
		rollback;
	end;
	start transaction;
	CALL pInsertarGenero('Ficción');
	CALL pInsertarGenero('No ficción');
	CALL pInsertarGenero('Poesía');
	CALL pInsertarGenero('Drama');
	CALL pInsertarGenero('Novela');
	CALL pInsertarGenero('Cuento');
	CALL pInsertarGenero('Ensayo');
	CALL pInsertarGenero('Biografía');
	CALL pInsertarGenero('Autobiografía');
	CALL pInsertarGenero('Ciencia ficción');
	CALL pInsertarGenero('Fantasía');
	CALL pInsertarGenero('Misterio');
	CALL pInsertarGenero('Suspense');
	CALL pInsertarGenero('Romance');
	CALL pInsertarGenero('Aventura');
	CALL pInsertarGenero('Histórico');
	CALL pInsertarGenero('Policial');
	CALL pInsertarGenero('Terror');
	CALL pInsertarGenero('Humor');
	CALL pInsertarGenero('Crónica');
	commit;
end //
delimiter ;

# aqui ya esta todo de momento
delimiter //
create procedure pInsertarNuevoLibro
(
IN pISBN varchar(14),
IN ptitulo varchar(100),
IN pnombreAutor varchar(40),
IN papellidoAutor varchar(40),
IN papellidoDosAutor varchar(40),
IN pnumeroPag int,
IN pgenero varchar(40)
)
begin 
	declare vGenero int;
    declare vLibro int;
    select count(*) into vLibro from libros
    where ISBN = pISBN;
    set vGenero = (select idGenero from generos where tipo = pgenero);
	
    if vLibro > 0 then
		signal sqlstate '45000'
        set message_text = 'El libro ya existe!';
	else
		insert into libros (ISBN, tituloLibro, AutorNombre, AutorApellido, AutorApellidoDos, numeroPaginas, Genero)
		values (pISBN, ptitulo, pnombreAutor, papellidoAutor, papellidoDosAutor, pnumeroPag, vGenero);
    end if;
end //
delimiter ;

delimiter //
create procedure pELiminarLibro
(
IN pISBN varchar (14)
)
begin
	declare vLibro int;
    select count(*) into vLibro from libros
    where ISBN = pISBN;
    if vLibro = 0 then
		signal sqlstate '45000'
        set message_text = 'El ISBN ingresado no existe!';
	else
		delete from libros where ISBN = pISBN;
	end if;
end //
delimiter ;

delimiter //
create procedure pActualizarTituloLibro
(
IN pTitulo varchar(40),
IN pISBN varchar(14)
)
begin 
	declare vLibro int;
    select count(*) into vLibro from libros
    where ISBN = pISBN;
    if vLibro = 0 then
		signal sqlstate '45000'
        set message_text = 'El ISBN ingresado no existe!';
	else
		update libros set tituloLibro = pTitulo where ISBN = pISBN;
	end if;
end //
delimiter ;

delimiter //
create procedure pActualizarNombreAutLibro
(
IN pNombre varchar(40),
IN pISBN varchar(14)
)
begin
	declare vLibro int;
    select count(*) into vLibro from libros
    where ISBN = pISBN;
    if vLibro = 0 then
		signal sqlstate '45000'
        set message_text = 'El ISBN ingresado no existe!';
	else
		update libros set AutorNombre = pNombre where ISBN = pISBN;
	end if;
end //
delimiter ;

delimiter //
create procedure pActualizarApellidoAutLibro
(
IN pApellido varchar(40),
IN pISBN varchar(14)
)
begin
	declare vLibro int;
    select count(*) into vLibro from libros
    where ISBN = pISBN;

    if vLibro = 0 then
		signal sqlstate '45000'
        set message_text = 'El ISBN ingresado no existe!';
	else
		update libros set AutorApellido = pApellido where ISBN = pISBN;
	end if;
end //
delimiter ;

delimiter //
create procedure pActualizarSegApellidoAutLibro
(
IN pApellidoDos varchar(40),
IN pISBN varchar(14)
)
begin
	declare vLibro int;
    declare vApellido int;
    select count(*) into vLibro from libros
    where ISBN = pISBN;
    
    if vLibro = 0 then
		signal sqlstate '45000'
        set message_text = 'El ISBN ingresado no existe!';
	else
		update libros set AutorApellidoDos = pApellidoDos where ISBN = pISBN;
	end if;
end //
delimiter ;

delimiter //
create procedure pActualizarPaginasLibro
(
IN pPaginas int,
IN pISBN varchar(14)
)
begin
	declare vLibro int;
    select count(*) into vLibro from libros
    where ISBN = pISBN;
    if vLibro = 0 then
		signal sqlstate '45000'
        set message_text = 'El ISBN ingresado no existe!';
	else
		update libros set numeroPaginas = pPaginas where ISBN = pISBN;
	end if;
end //
drop procedure pActualizarGeneroLibro;
delimiter ;

delimiter //
create procedure pActualizarGeneroLibro
(
IN pGenero varchar(40),
IN pISBN varchar(14)
)
begin
	declare vGenero int;
    declare vLibro int;
    set vGenero = (select idGenero from generos where tipo = pGenero);
    
    select count(*) into vLibro from libros
    where ISBN = pISBN;
    if vLibro = 0 then
		signal sqlstate '45000'
        set message_text = 'El ISBN ingresado no existe!';
	else
		update libros set Genero = vGenero where ISBN = pISBN;
    end if;
end //
delimiter ;

delimiter //
create procedure pActualizarTodoAutor
(
IN pNombreNuevo varchar (60),
IN pApellidoNuevo varchar(60),
IN pApellidoDosNuevo varchar(60),
IN pNombre varchar(60),
IN pApellido varchar(60),
IN pApellidoDos varchar(60),
IN pPais varchar(60)
)
begin 
	declare vPais int;
    set vPais = (select idPais from paises where nombrePais = pPais);
	update autores set nombreAutor = pNombreNuevo, apellidoAutor = pApellidoNuevo, apellidoDosAutor = pApellidoDosNuevo, pais = vPais
    where nombreAutor = pNombre and apellidoAutor = pApellido and apellidoDosAutor = pApellidoDos;
end //
delimiter ;

delimiter //
create procedure pActualizarTodoSinPais
(
IN pNombreNuevo varchar (60),
IN pApellidoNuevo varchar(60),
IN pApellidoDosNuevo varchar(60),
IN pNombre varchar(60),
IN pApellido varchar(60),
IN pApellidoDos varchar(60)
)
begin
	update autores set nombreAutor = pNombreNuevo, apellidoAutor = pApellidoNuevo, apellidoDosAutor = pApellidoDosNuevo
    where nombreAutor = pNombre and apellidoAutor = pApellido and apellidoDosAutor = pApellidoDos;
    end //
delimiter ;

delimiter //
create procedure pActualizarTodoAutor
(
IN pNombreNuevo varchar (60),
IN pApellidoNuevo varchar(60),
IN pApellidoDosNuevo varchar(60),
IN pPaisNuevo varchar(40),
IN pNombreActual varchar(60),
IN pApellidoActual varchar(60),
IN pApellidoDosActual varchar(60)
)
begin 
	declare vPais int;
    select idPais into vPais from paises where nombrePais = pPaisNuevo;
	update autores set nombreAutor = pNombreNuevo,
	apellidoAutor = pApellidoNuevo,
	apellidoDosAutor = pApellidoDosNuevo,
	pais = vPais
    where nombreAutor = pNombreActual and
    apellidoAutor = pApellidoActual and
    apellidoDosAutor = pApellidoDosActual;
end //
delimiter ;
call pActualizarTodoAutor("Carlas", "Carlas", "Carlas", "Alemania", "maria", "gutierrez", "herrando");

update autores set pais = 2 where nombreAutor = "Carlos"
and apellidoAutor = "Carlos" and
apellidoDosAutor = "Carlos";

delimiter //
create procedure pActualizarTodoSinPais
(
IN pNombreNuevo varchar (60),
IN pApellidoNuevo varchar(60),
IN pApellidoDosNuevo varchar(60),
IN pNombre varchar(60),
IN pApellido varchar(60),
IN pApellidoDos varchar(60)
)
begin
	update autores set nombreAutor = pNombreNuevo, apellidoAutor = pApellidoNuevo, apellidoDosAutor = pApellidoDosNuevo
    where nombreAutor = pNombre and apellidoAutor = pApellido and apellidoDosAutor = pApellidoDos;
    end //
delimiter ;

delimiter //
create procedure pActulizarTodoSinSegundoApellido
(
IN pNombreNuevo varchar(60),
IN pApellidoNuevo varchar(60),
IN pPaisNuevo varchar(60),
IN pNombreActual varchar(60),
IN pApellidoActual varchar(60),
IN pApellidoDosActual varchar(60)
)
begin
	declare vPais int;
    select idPais into vPais from paises where nombrePais = pPaisNuevo;
	update autores set nombreAutor = pNombreNuevo, 
    apellidoAutor = pApellidoNuevo,
    pais = vPais
    where nombreAutor = pNombreActual and
    apellidoAutor = pApellidoActual and
    apellidoDosAutor = pApellidoDosActual;
end //
delimiter ;

delimiter //
create procedure pActualizarTodoSinApellido
(
IN pNombreNuevo varchar(60),
IN pApellidoDosNuevo varchar(60),
IN pPaisNuevo varchar(60),
IN pNombreActual varchar(60),
IN pApellidoActual varchar(60),
IN pApellidoDosActual varchar(60)
)
begin
	declare vPais int;
    select idPais into vPais from paises where nombrePais = vPais;
	update autor set nombreAutor = pNombreNuevo,
    apellidoDosAutor = pApellidoDosNuevo,
    pais = vPais
    where nombreAutor = pNombreActual and
    apellidoAutor = pApellidoActual and
    apellidoDosAutor = pApellidoDosActual;
end //
delimiter ;
delimiter //
create procedure pActualizarTodoSinNombre
(
IN pApellidoNuevo varchar(60),
IN pApellidoDosNuevo varchar(60),
IN pPaisNuevo varchar(60),
IN pnombreActual varchar(60),
IN pApellidoActual varchar(60),
IN pApellidoDosActual varchar(60)
)
begin
	declare vPais int;
    select idPais into vPais from paises where nombrePais = pPaisNuevo;
    update autores set apellidoAutor = pApellidoNuevo,
    apellidoDosAutor = pApellidoDosNuevo,
    pais = vPais
    where nombreAutor = pNombreActual and
    apellidoAutor = pApellidoActual and
    apellidoDosAutor = pApellidoDosActual;
end //
delimiter ;

drop procedure pActualizarNombreApellido;
delimiter //
create procedure pActualizarNombreApellido
(
IN pNombreNuevo varchar(60),
IN PApellidoNuevo varchar(60),
IN pNombreActual varchar(60),
IN pApellidoActual varchar(60),
IN pApellidoDosActual varchar(60)
)
begin
	update autores set nombreAutor = pNombreNuevo, apellidoAutor = pApellidoNuevo
    where nombreAutor = pNombreActual and
    apellidoAutor = pApellidoActual and
    apellidoDosAutor = pApellidoDosActual;
end //
delimiter ;

delimiter //
create procedure pActualizarNombreSegundoApellido
(
IN pNombreNuevo varchar(60),
IN pApellidoDosNuevo varchar(60),
IN pNombreActual varchar(60),
IN pApellidoActual varchar(60),
IN pApellidoDosActual varchar(60)
)
begin
	update autores set nombreAutor = pNombreNuevo, apellidoDosAutor = pApellidoDosNuevo
    where nombreAutor = pNombreActual and
    apellidoAutor = pApellidoActual and
    apellidoDosAutor = pApellidoDosActual;
end //
delimiter ;

delimiter //
create procedure pActualizarNombrePais
(
IN pNombreNuevo varchar(60),
IN pPaisNuevo varchar(60),
IN pNombreActual varchar(60),
IN pApellidoActual varchar(60),
IN pApellidoDosActual varchar(60)
)
begin
	declare vPais int;
    select idPais into vPais from paises where nombrePais = pPaisNuevo;
    update autores set nombreAutor = pNombreNuevo, pais = vPais
    where nombreAutor = pNombreActual and
    apellidoAutor = pApellidoActual and
    apellidoDosAutor = pApellidoDosActual;
end //
delimiter ;
delimiter //
create procedure pActualizarApellidos
(
IN pApellidoNuevo varchar(60),
IN pApellidoDosNuevo varchar(60),
IN pNombreActual varchar(60),
IN pApellidoActual varchar(60),
IN pApellidoDosActual varchar(60)
)
begin
	update autores set apellidoAutor = pApellidoNuevo,
    apellidoDosAutor = pApellidoDosNuevo
    where nombreAutor = pNombreActual and 
    apellidoAutor = pApellidoActual and
    apellidoDosAutor = pApellidoDosActual;
end //
delimiter ;
delimiter //
create procedure pActualizarApellidoPais
(
IN pApellidoNuevo varchar(60),
IN pPaisNuevo varchar(60),
IN pNombreActual varchar(60),
IN pApellidoActual varchar(60),
IN pApellidoDosActual varchar(60)
)
begin
	declare vPais int;
    select idPais into vPais from paises where nombrePais = pPaisNuevo;
    update autores set apellidoAutor = pApellidoNuevo,
    pais = vPais
    where nombreAutor = pNombreActual and
    apellidoAutor = pApellidoActual and 
    apellidoDosAutor = pApellidoDosActual;
end //
delimiter ;
delimiter //
create procedure pActualizarSegundoApellidoPais
(
IN pApellidoDosNuevo varchar(60),
IN pPaisNuevo varchar(60),
IN pNombreActual varchar(60),
IN pApellidoActual varchar(60),
IN pApellidoDosActual varchar(60)
)
begin
	declare vPais int;
    select idPais into vPais from paises where nombrePais = pPaisNuevo;
    update autores set apellidoDosAutor = pApellidoDosNuevo,
    pais = vPais
    where nombreAutor = pNombreActual and
    apellidoAutor = pApellidoActual and 
    apellidoDosAutor = pApellidoDosActual;
end //
delimiter ;

