import React from 'react';
import './Searchbar.css';

const SearchBar: React.FC = () => {
  return (
    <div className="search">
      <input
        className="search__input"
        type="text"
        placeholder="Search raffles"
      />
    </div>
  );
};

export default SearchBar;
