import { connect } from "react-redux";
import * as actions from '../actions/actions';
import App from "./App";

const mapDispatchToProps = {
  loggedIn: actions.loggedIn
};

export default connect(() => {}, mapDispatchToProps)(App);