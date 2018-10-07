import 'package:flutter/material.dart';

void main() {
  runApp(new FriendlychatApp());
}

class FriendlychatApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
      title: "Friendlychat",
      home: new ChatScreen(),
    );
  }
}

class ChatScreen extends StatefulWidget {                     //modified
  @override                                                        //new
  State createState() => new ChatScreenState();                    //new
}

// Add the ChatScreenState class definition in main.dart.

class ChatScreenState extends State<ChatScreen> {                  //new
  @override                                                        //new
  Widget build(BuildContext context) {
    return new Scaffold(
      appBar: new AppBar(title: new Text("Friendlychat")),

    );
  }
  Widget _buildTextComposer() {
    return new Container(
      margin: const EdgeInsets.symmetric(horizontal: 8.0),
      child: new TextField(
        controller: _textController,
        onSubmitted: _handleSubmitted,
        decoration: new InputDecoration.collapsed(
            hintText: "Send a message"),
      ),
    );
  }
  final TextEditingController _textController = new TextEditingController(); //new
  void _handleSubmitted(String text) {
    _textController.clear();
  }

}



