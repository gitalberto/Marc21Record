# Marc21Record
Estrutura de dados em Java para manipulação de arquivos no formato Marc

# Exemplos de Uso da Estrutura Marc21Record

Aqui estão exemplos de como usar a estrutura de dados `Marc21Record` para representar e manipular registros MARC21 em Java.

```java

import java.util.List;

// Importe a classe Marc21Record
import Marc21Record;

public class Main {
    public static void main(String[] args) {
        // Criar um novo registro MARC21
        Marc21Record record = new Marc21Record();

        // Adicionar alguns campos e subcampos ao registro
        record.addField("001", new char[] { ' ', ' ' });
        record.getFields().get(0).addSubfield('a', "123456789");

        record.addField("245", new char[] { '1', '0' });
        record.getFields().get(1).addSubfield('a', "Título do Livro");
        record.getFields().get(1).addSubfield('b', "Subtítulo");

        // Exibir o registro MARC21
        System.out.println("Registro MARC21:");
        System.out.println(record);

        // Usar as funções adicionais

        // 1. Remover um campo por tag
        record.removeFieldByTag("001");

        // 2. Obter um campo por tag
        Marc21Record.Marc21Field field = record.getFieldByTag("245");
        if (field != null) {
            System.out.println("\nCampo 245 encontrado:");
            System.out.println("Field Tag: " + field.getTag());
            System.out.println("Indicators: " + field.getIndicators());
        } else {
            System.out.println("\nCampo 245 não encontrado.");
        }

        // 3. Verificar se um campo específico existe
        boolean exists = record.fieldExists("001");
        System.out.println("\nCampo 001 existe: " + exists);

        // 4. Contar o número de campos no registro
        int fieldCount = record.getFieldCount();
        System.out.println("\nNúmero de campos no registro: " + fieldCount);

        // 5. Obter todos os subcampos de um campo específico por tag
        List<Marc21Record.Marc21Subfield> subfields = record.getSubfieldsByTag("245");
        if (!subfields.isEmpty()) {
            System.out.println("\nSubcampos do campo 245:");
            for (Marc21Record.Marc21Subfield subfield : subfields) {
                System.out.println("Subfield Code: " + subfield.getCode());
                System.out.println("Subfield Value: " + subfield.getValue());
            }
        } else {
            System.out.println("\nSubcampos do campo 245 não encontrados.");
        }

        // 6. Adicionar um campo inteiro com subcampos de uma vez
        Marc21Record.Marc21Field newField = new Marc21Record.Marc21Field("100", new char[] { '1', ' ' });
        newField.addSubfield('a', "Autor");
        newField.addSubfield('d', "Data de Nascimento");
        record.addField(newField);

        // Exibir o registro atualizado
        System.out.println("\nRegistro MARC21 atualizado:");
        System.out.println(record);

        // 7. Limpar todos os campos do registro
        record.clearFields();

        // Verificar se todos os campos foram removidos
        fieldCount = record.getFieldCount();
        System.out.println("\nNúmero de campos no registro após limpeza: " + fieldCount);
    }
}

