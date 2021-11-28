insert into member (full_name, birth_date, address) values ('Wawan Setiawan', '1990-01-10', 'kompleks Asia Serasi No 100');
insert into member (full_name, birth_date, address) values ('Teguh Sudibyantoron', '1991-02-10', 'Jalan Pemekaran No 99');
insert into member (full_name, birth_date, address) values ('Joko Widodo', '1992-03-10', 'Dusun Pisang Rt 10 Rw 20');

insert into transaction(member_id, amount, trx_type, trx_date, description) values (1, 1000000.0, 'C', '2020-08-17 00:00:00', 'menyerahkan dana');
insert into transaction(member_id, amount, trx_type, trx_date, description) values (2, 5000000.0, 'C', '2020-08-18 00:00:00', 'menyerahkan dana');
insert into transaction(member_id, amount, trx_type, trx_date, description) values (3, 2000000.0, 'D', '2020-09-30 00:00:00', 'meminjam dana');
insert into transaction(member_id, amount, trx_type, trx_date, description) values (3, 1000000.0, 'C', '2020-11-10 00:00:00', 'mengembalikan dana');
insert into transaction(member_id, amount, trx_type, trx_date, description) values (1, 5000000.0, 'C', '2020-12-01 00:00:00', 'menyerahkan dana');
insert into transaction(member_id, amount, trx_type, trx_date, description) values (2, 2000000.0, 'D', '2020-12-01 00:00:00', 'mengambil dana');