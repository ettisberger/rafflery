import React, {Component} from 'react';
import './Home.css';
import RaffleList from '../../components/raffles/RaffleList';
import SearchBar from '../../components/Searchbar';
import axios from 'axios';

interface HomeState  {
    raffles: any;
}

class Home extends Component<{}, HomeState> {
    constructor(props: any) {
        super(props);

        this.state = {raffles: []};
    }

    public componentDidMount(): void {
        const jwt = localStorage.getItem('id_token');
        console.log('using jwt', jwt);

        axios.get('/api/raffles', {
            headers: {
                Authorization: 'Bearer ' + jwt,
            },
        })
            .then(res => {
                console.log(res.data);
                this.setState({raffles: res.data});
            });
    }

    public render() {
        return (
            <div className="home">
                <SearchBar/>
                <RaffleList raffles={this.state.raffles}/>
            </div>
        );
    }

}

export default Home;
