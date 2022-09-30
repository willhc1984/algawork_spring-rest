INSERT INTO tb_cliente(nome, email, telefone) VALUES ('Joselito', 'joselito@teste.com', '1547856965');
INSERT INTO tb_cliente(nome, email, telefone) VALUES ('Maria Silva', 'maria@teste.com', '1541256988');
INSERT INTO tb_cliente(nome, email, telefone) VALUES ('Carlos Andrade', 'carlos@teste.com', '1532658745');
INSERT INTO tb_cliente(nome, email, telefone) VALUES ('Silvia Souza', 'silvia@teste.com', '1547856325');
INSERT INTO tb_cliente(nome, email, telefone) VALUES ('Silvia Albuquerque', 'silviaalb@teste.com', '1547896523');
INSERT INTO tb_cliente(nome, email, telefone) VALUES ('Maria Joana', 'mj@teste.com', '25412589');
INSERT INTO tb_cliente(nome, email, telefone) VALUES ('Ana Paula Souza', 'apss@teste.com', '1523665478');
INSERT INTO tb_cliente(nome, email, telefone) VALUES ('Carlos Alberto', 'carloalb@teste.com', '2523412589');
INSERT INTO tb_cliente(nome, email, telefone) VALUES ('João Paulo Cesar', 'jp@teste.com', '36542587');

INSERT INTO tb_entrega(data_finalizacao, data_pedido, destinatario_bairro, destinatario_complemento, destinatario_logradouro, destinatario_nome, destinatario_numero, status, taxa, cliente_id) VALUES ('2022-05-16', '2022-05-16', 'São Marcos', 'casa', 'av itavuvu', 'João', '25', 'FINALIZADA', 50, 1);

INSERT INTO tb_entrega(data_finalizacao, data_pedido, destinatario_bairro, destinatario_complemento, destinatario_logradouro, destinatario_nome, destinatario_numero, status, taxa, cliente_id) VALUES ('2022-05-16', '2022-05-16', 'São Marcos', 'casa', 'av itavuvu', 'João', '25', 'PENDENTE', 50, 1);
INSERT INTO tb_entrega(data_finalizacao, data_pedido, destinatario_bairro, destinatario_complemento, destinatario_logradouro, destinatario_nome, destinatario_numero, status, taxa, cliente_id) VALUES ('2022-05-16', '2022-05-16', 'São Marcos', 'casa', 'av itavuvu', 'Marcos', '25', 'PENDENTE', 50, 2);
INSERT INTO tb_entrega(data_finalizacao, data_pedido, destinatario_bairro, destinatario_complemento, destinatario_logradouro, destinatario_nome, destinatario_numero, status, taxa, cliente_id) VALUES ('2022-05-16', '2022-05-16', 'São Marcos', 'casa', 'av itavuvu', 'Lucas', '25', 'PENDENTE', 50, 2);
INSERT INTO tb_entrega(data_finalizacao, data_pedido, destinatario_bairro, destinatario_complemento, destinatario_logradouro, destinatario_nome, destinatario_numero, status, taxa, cliente_id) VALUES ('2022-05-16', '2022-05-16', 'São Marcos', 'casa', 'av itavuvu', 'Pedro', '25', 'PENDENTE', 50, 3);
INSERT INTO tb_entrega(data_finalizacao, data_pedido, destinatario_bairro, destinatario_complemento, destinatario_logradouro, destinatario_nome, destinatario_numero, status, taxa, cliente_id) VALUES ('2022-05-16', '2022-05-16', 'São Marcos', 'casa', 'av ipanema', 'Maria', '25', 'PENDENTE', 50, 4);

INSERT INTO tb_ocorrencia(data_registro, descricao, entrega_id) VALUES('2022-05-16', 'Cliente não estava na casa', 1);
INSERT INTO tb_ocorrencia(data_registro, descricao, entrega_id) VALUES('2022-05-18', 'Cliente não estava na casa', 1);