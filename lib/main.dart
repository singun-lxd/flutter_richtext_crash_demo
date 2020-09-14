import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'RichText Crash Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: Scaffold(
        backgroundColor: Colors.white,
        body: Center(
          child: RichText(
            text: TextSpan(children: [
              WidgetSpan(child: SizedBox.shrink()),
              TextSpan(
                text: "Text",
                style: TextStyle(color: Colors.black),
                recognizer: TapGestureRecognizer()..onTap = () {},
              )
            ]),
          ),
        ),
      ),
    );
  }
}
