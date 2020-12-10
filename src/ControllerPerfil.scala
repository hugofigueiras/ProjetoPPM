import java.text.SimpleDateFormat
import java.util
import java.util.Calendar

import CSVFileReader.{changeEmail, changePassword, changeUsername, credentialsFile, readFile, writeFile}
import IO.{email, getUserInput, newEmailPrint, newPasswordPrint, newUsernamePrint, showSettings}
import MenuUserUtils.roundAt
import javafx.collections.{FXCollections, ObservableList}
import javafx.fxml.FXML
import javafx.scene.control.{Button, ChoiceBox, ComboBox, Labeled, RadioButton, TextField}

class ControllerPerfil {
  @FXML
  private var textField: TextField = _
  @FXML
  private var usernameButton: RadioButton=_
  @FXML
  private var passButton: RadioButton=_
  @FXML
  private var emailButton: RadioButton=_
  @FXML
  private var novoLabel:Labeled=_
  @FXML
  private var confirmButton:Button=_

  var tempUser: UserApp= new UserApp("","","",0.0,LazyList[UserList](),LazyList[UserList](),List[String](),List[(String,Double)](),List[categorySavings](),new PlanSoft(10,List[categorySavings]()))
  var parent:ControllerMenu=new ControllerMenu
  //setter

  def getUser():UserApp={
    tempUser
  }

  def setParent(parent: ControllerMenu): Unit ={
    this.parent=parent
  }
  def setTempUser(tempUser: UserApp): Unit ={
    this.tempUser=tempUser
  }

  def onConfirmButtonClicked():Unit={
      parent.setUser(profile(tempUser,textField.getText()))
      confirmButton.getScene().getWindow.hide()
  }


  def profile(user: UserApp,novoValue:String): UserApp = {
    if(usernameButton.isSelected) {
        val lines = readFile(credentialsFile)
        changeUsername(user,novoValue,lines,"")
        val newUser:UserApp = user.copy(name = novoValue,user.email,user.password, balance = user.balance, depositList = user.depositList, expenseList = user.expenseList, user.userCategories,user.monthlySavings,user.catSavList,user.plan)
        newUser
      } else if (passButton.isSelected){
        val lines = readFile(credentialsFile)
        changeEmail(user,novoValue,lines,"")
        val newUser:UserApp = user.copy(name = user.name,novoValue,user.password, balance = user.balance, depositList = user.depositList, expenseList = user.expenseList, user.userCategories, user.monthlySavings,user.catSavList,user.plan)
        newUser
      }else if(emailButton.isSelected){
        val lines = readFile(credentialsFile)
        changePassword(user,novoValue,lines,"")
        val newUser :UserApp = user.copy(name = user.name,user.email,novoValue, balance = user.balance, depositList = user.depositList, expenseList = user.expenseList, userCategories=user.userCategories, monthlySavings = user.monthlySavings,catSavList =user.catSavList,user.plan)
        newUser
      }else user

    }


}