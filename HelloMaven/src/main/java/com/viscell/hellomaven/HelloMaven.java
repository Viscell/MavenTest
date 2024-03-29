package com.viscell.hellomaven;

import java.util.Random;
import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
 
/**
 * @web http://java-buddy.blogspot.com/
 */
public class HelloMaven extends Application {
 
    private TableView tableView = new TableView();
    private Button btnNew = new Button("New Record");
 
    static Random random = new Random();
 
    static final String Day[] = {
        "Monday",
        "Tuesday",
        "Wednesday",
        "Thursday",
        "Friday"};
 
    public static class Record {
 
        private final SimpleIntegerProperty id;
        private final SimpleIntegerProperty value_0;
        private final SimpleIntegerProperty value_1;
        private final SimpleIntegerProperty value_2;
        private final SimpleIntegerProperty value_3;
        private final SimpleIntegerProperty value_4;
 
        Record(int i, int v0, int v1, int v2, int v3,
                int v4) {
            this.id = new SimpleIntegerProperty(i);
            this.value_0 = new SimpleIntegerProperty(v0);
            this.value_1 = new SimpleIntegerProperty(v1);
            this.value_2 = new SimpleIntegerProperty(v2);
            this.value_3 = new SimpleIntegerProperty(v3);
            this.value_4 = new SimpleIntegerProperty(v4);
        }
 
        public int getId() {
            return id.get();
        }
 
        public void setId(int v) {
            id.set(v);
        }
 
        public int getValue_0() {
            return value_0.get();
        }
 
        public void setValue_0(int v) {
            value_0.set(v);
        }
 
        public int getValue_1() {
            return value_1.get();
        }
 
        public void setValue_1(int v) {
            value_1.set(v);
        }
 
        public int getValue_2() {
            return value_2.get();
        }
 
        public void setValue_2(int v) {
            value_2.set(v);
        }
 
        public int getValue_3() {
            return value_3.get();
        }
 
        public void setValue_3(int v) {
            value_3.set(v);
        }
 
        public int getValue_4() {
            return value_4.get();
        }
 
        public void setValue_4(int v) {
            value_4.set(v);
        }
 
    };
 
    ObservableList<Record> data = FXCollections.observableArrayList();
 
    @Override
    public void start(final Stage primaryStage) {
        primaryStage.setTitle("java-buddy.blogspot.com");
        tableView.setEditable(true);
        Callback<TableColumn, TableCell> cellFactory
                = new Callback<TableColumn, TableCell>() {
 
                    @Override
                    public TableCell call(TableColumn p) {
                        return new EditingCell();
                    }
                };
 
        btnNew.setOnAction(btnNewHandler);
 
        //init table
        //Un-editable column of "id"
        TableColumn col_id = new TableColumn("ID");
        tableView.getColumns().add(col_id);
        col_id.setCellValueFactory(
                new PropertyValueFactory<Record, String>("id"));
 
        //Editable columns
        for (int i = 0; i < Day.length; i++) {
            TableColumn col = new TableColumn(Day[i]);
            col.setCellValueFactory(
                    new PropertyValueFactory<Record, String>(
                            "value_" + String.valueOf(i)));
            tableView.getColumns().add(col);
            col.setCellFactory(cellFactory);
             
            final int idx = i;
            col.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Record, Integer>>() {
 
                @Override
                public void handle(TableColumn.CellEditEvent<Record, Integer> event) {
                    Record rec = event.getTableView().getItems().get(event.getTablePosition().getRow());
                    Integer newValue = event.getNewValue();
                     
                    switch(idx){
                        case 0: rec.setValue_0(newValue);
                                break;
                        case 1: rec.setValue_1(newValue);
                                break;
                        case 2: rec.setValue_2(newValue);
                                break;
                        case 3: rec.setValue_3(newValue);
                                break;
                        case 4: rec.setValue_4(newValue);
                                break;
                    }
                }
            });
 
        }
 
        //Insert Button
        TableColumn col_action = new TableColumn("Action");
        col_action.setSortable(false);
 
        col_action.setCellValueFactory(
            new Callback<TableColumn.CellDataFeatures<Record, Boolean>, ObservableValue<Boolean>>() {
 
                @Override
                public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Record, Boolean> p) {
                    return new SimpleBooleanProperty(p.getValue() != null);
                }
            });
 
        col_action.setCellFactory(
            new Callback<TableColumn<Record, Boolean>, TableCell<Record, Boolean>>() {
 
                @Override
                public TableCell<Record, Boolean> call(TableColumn<Record, Boolean> p) {
                    return new ButtonCell(tableView);
                }
 
            });
        tableView.getColumns().add(col_action);
 
        tableView.setItems(data);
 
        Group root = new Group();
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.getChildren().addAll(btnNew, tableView);
        root.getChildren().add(vBox);
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
 
    public class SubRecord {
 
        private SimpleStringProperty fieldSubRecordName;
        private SimpleIntegerProperty fieldSubRecordValue;
 
        SubRecord(String sn, int sv) {
            this.fieldSubRecordName = new SimpleStringProperty(sn);
            this.fieldSubRecordValue = new SimpleIntegerProperty(sv);
        }
 
        public String getFieldSubRecordName() {
            return fieldSubRecordName.get();
        }
 
        public int getFieldSubRecordValue() {
            return fieldSubRecordValue.get();
        }
 
    }
 
    //Define the button cell
    private class ButtonCell extends TableCell<Record, Boolean> {
 
        final Button cellButton = new Button("Action");
 
        ButtonCell(final TableView tblView) {
 
            cellButton.setOnAction(new EventHandler<ActionEvent>() {
 
                @Override
                public void handle(ActionEvent t) {
                    int selectdIndex = getTableRow().getIndex();
 
                    //Create a new table show details of the selected item
                    Record selectedRecord = (Record) tblView.getItems().get(selectdIndex);
                    ObservableList<SubRecord> subDataList
                            = FXCollections.observableArrayList(
                                    new SubRecord("ID", selectedRecord.getId()),
                                    new SubRecord("Monday", selectedRecord.getValue_0()),
                                    new SubRecord("Tuesday", selectedRecord.getValue_1()),
                                    new SubRecord("Wednesday", selectedRecord.getValue_2()),
                                    new SubRecord("Thursday", selectedRecord.getValue_3()),
                                    new SubRecord("Friday", selectedRecord.getValue_4()));
 
                    TableColumn columnfield = new TableColumn("Field");
                    columnfield.setCellValueFactory(
                            new PropertyValueFactory<Record, String>("fieldSubRecordName"));
 
                    TableColumn columnValue = new TableColumn("Value");
                    columnValue.setCellValueFactory(
                            new PropertyValueFactory<SubRecord, Integer>("fieldSubRecordValue"));
 
                    TableView<SubRecord> subTableView = new TableView<>();
                    subTableView.setItems(subDataList);
                    subTableView.getColumns().addAll(columnfield, columnValue);
 
                    Stage myDialog = new Stage();
                    myDialog.initModality(Modality.WINDOW_MODAL);
 
                    Scene myDialogScene = new Scene(VBoxBuilder.create()
                            .children(subTableView)
                            .alignment(Pos.CENTER)
                            .padding(new Insets(10))
                            .build());
 
                    myDialog.setScene(myDialogScene);
                    myDialog.show();
                }
            });
        }
 
        //Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                setGraphic(cellButton);
            }
        }
    }
 
    EventHandler<ActionEvent> btnNewHandler
        = new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent t) {
 
                //generate new Record with random number
                int newId = data.size();
                Record newRec = new Record(
                    newId,
                    random.nextInt(100),
                    random.nextInt(100),
                    random.nextInt(100),
                    random.nextInt(100),
                    random.nextInt(100));
                data.add(newRec);
            }
        };
 
    class EditingCell extends TableCell<XYChart.Data, Number> {
 
        private TextField textField;
 
        public EditingCell() {
        }
 
        @Override
        public void startEdit() {
 
            super.startEdit();
 
            if (textField == null) {
                createTextField();
            }
 
            setGraphic(textField);
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            textField.selectAll();
        }
 
        @Override
        public void cancelEdit() {
            super.cancelEdit();
 
            setText(String.valueOf(getItem()));
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        }
 
        @Override
        public void updateItem(Number item, boolean empty) {
            super.updateItem(item, empty);
 
            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(getString());
                    }
                    setGraphic(textField);
                    setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                } else {
                    setText(getString());
                    setContentDisplay(ContentDisplay.TEXT_ONLY);
                }
            }
        }
 
        private void createTextField() {
            textField = new TextField(getString());
            textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
            textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
 
                @Override
                public void handle(KeyEvent t) {
                    if (t.getCode() == KeyCode.ENTER) {
                        commitEdit(Integer.parseInt(textField.getText()));
                    } else if (t.getCode() == KeyCode.ESCAPE) {
                        cancelEdit();
                    }
                }
            });
        }
 
        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }
    }
 
}