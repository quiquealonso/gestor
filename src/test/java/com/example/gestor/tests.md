pm.test("Listado responde 200", function () {
    pm.response.to.have.status(200);
});

pm.test("Listado devuelve un array", function () {
    const cuerpo = pm.response.json();
    pm.expect(cuerpo).to.be.an("array");
});

pm.test("Listado vacío también es válido", function () {
    const cuerpo = pm.response.json();
    pm.expect(cuerpo).to.be.an("array");
});
pm.test("Listado filtrado responde 200", function () {
    pm.response.to.have.status(200);
});

pm.test("Listado filtrado devuelve un array", function () {
    const cuerpo = pm.response.json();
    pm.expect(cuerpo).to.be.an("array");
});

pm.test("Todos los elementos cumplen el filtro", function () {
    const cuerpo = pm.response.json();
    cuerpo.forEach(function (tarea) {
        pm.expect(tarea.completada).to.equal(true);
    });
});
pm.test("Listado filtrado responde 200", function () {
    pm.response.to.have.status(200);
});

pm.test("Listado filtrado devuelve un array", function () {
    const cuerpo = pm.response.json();
    pm.expect(cuerpo).to.be.an("array");
});

pm.test("Todos los elementos cumplen el filtro", function () {
    const cuerpo = pm.response.json();
    cuerpo.forEach(function (tarea) {
        pm.expect(tarea.completada).to.equal(true);
    });
});
pm.test("Detalle existente responde 200", function () {
    pm.response.to.have.status(200);
});

pm.test("Detalle devuelve el objeto esperado", function () {
    const cuerpo = pm.response.json();
    pm.expect(cuerpo).to.have.property("id");
    pm.expect(cuerpo).to.have.property("titulo");
    pm.expect(cuerpo).to.have.property("prioridad");
    pm.expect(cuerpo).to.have.property("completada");
});
pm.test("Detalle ausente responde 404", function () {
    pm.response.to.have.status(404);
});

pm.test("Detalle ausente no devuelve cuerpo", function () {
    pm.expect(pm.response.text()).to.equal("");
});
pm.test("PUT existente responde 200", function () {
    pm.response.to.have.status(200);
});

pm.test("PUT sustituye la representación", function () {
    const cuerpo = pm.response.json();
    pm.expect(cuerpo.id).to.equal(Number(pm.collectionVariables.get("tareaBId")));
    pm.expect(cuerpo.titulo).to.equal("Tarea B actualizada");
    pm.expect(cuerpo.prioridad).to.equal("alta");
    pm.expect(cuerpo.completada).to.equal(true);
});
pm.test("PUT ausente responde 404", function () {
    pm.response.to.have.status(404);
});

pm.test("PUT ausente no devuelve cuerpo", function () {
    pm.expect(pm.response.text()).to.equal("");
});
pm.test("PATCH existente responde 200", function () {
    pm.response.to.have.status(200);
});

pm.test("PATCH modifica solo los campos admitidos", function () {
    const cuerpo = pm.response.json();
    pm.expect(cuerpo.id).to.equal(Number(pm.collectionVariables.get("tareaAId")));
    pm.expect(cuerpo.titulo).to.equal("Tarea A parcheada");
    pm.expect(cuerpo.prioridad).to.equal("media");
    pm.expect(cuerpo.completada).to.be.a("boolean");
});
pm.test("DELETE responde 204", function () {
    pm.response.to.have.status(204);
});

pm.test("DELETE no devuelve cuerpo", function () {
    pm.expect(pm.response.text()).to.equal("");
});
pm.test("Creación con 201", function () {
    pm.response.to.have.status(201);
});
pm.test("Id asignado y contenido esperado", function () {
    const cuerpo = pm.response.json();
    pm.expect(cuerpo.id).to.be.above(0);
    pm.expect(cuerpo.titulo).to.equal("Prueba A de la colección");
});
pm.test("Incluye Location", function () {
    pm.expect(pm.response.headers.get("Location")).to.be.a("string").and.not.empty;
});
pm.test("Devuelve exactamente B", function () {
    const cuerpo = pm.response.json();
    pm.expect(cuerpo.id).to.equal(Number(pm.collectionVariables.get("tareaBId")));
    pm.expect(cuerpo.titulo).to.equal("Prueba B de la colección");
});
pm.test("Borrado con 204 y sin cuerpo", function () {
    pm.response.to.have.status(204);
    pm.expect(pm.response.text()).to.equal("");
});

pm.test("Proyecto POST inválido responde 400", function () {
    pm.response.to.have.status(400);
});

pm.test("Proyecto PUT inválido responde 400", function () {
    pm.response.to.have.status(400);
});

pm.test("Proyecto PATCH inválido responde 400", function () {
    pm.response.to.have.status(400);
});