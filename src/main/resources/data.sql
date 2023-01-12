INSERT INTO PESSOA (id, cpf, nome, email, telefone, status, login, senha) VALUES ('1', '1234', 'Micael Trivelato', 'micael.hyppolito@deal.com.br', '11999999999', 'Ativo', 'micael3600', '1234');
INSERT INTO PESSOA (id, cpf, nome, email, telefone, status, login, senha) VALUES ('2', '1234', 'Micael Trivelato', 'micael.hyppolito@deal.com.br', '11999999999', 'Ativo', 'micael3600', '1234');

INSERT INTO CARTEIRA (id, saldo, situacao, id_pessoa) VALUES (1, 5042.98, 1, 1);
INSERT INTO CARTEIRA (id, saldo, situacao, id_pessoa) VALUES (2, 10000.00, 1, 2);

INSERT INTO CHAVESPIX (id, tipo_chave, descricao) VALUES (1, 0, '11999999999');
INSERT INTO CHAVESPIX (id, tipo_chave, descricao) VALUES (2, 1, 'email@email.com');
INSERT INTO CHAVESPIX (id, tipo_chave, descricao) VALUES (3, 2, '4044040430');
INSERT INTO CHAVESPIX (id, tipo_chave, descricao) VALUES (4, 3, 'j48yf61n3i7kj8g1nuk5y478hgu1k4n5hu3gm1k45hgm1kl4h53');
INSERT INTO CHAVESPIX (id, tipo_chave, descricao) VALUES (5, 2, '2022020230');

INSERT INTO CARTEIRA_CHAVE_PIX (carteira_id, chave_pix_id) VALUES (1, 2);
INSERT INTO CARTEIRA_CHAVE_PIX (carteira_id, chave_pix_id) VALUES (1, 1);
INSERT INTO CARTEIRA_CHAVE_PIX (carteira_id, chave_pix_id) VALUES (2, 3);

INSERT INTO TRANSFERENCIA (id, id_Carteira_Pagadora, id_Carteira_Recebedora, valor, data) VALUES (1, 1, 2, 67.02, '2022-12-16 13:17:42');
